package transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

class InMemoryValidationService implements ValidationService {


    /**
     * In-memory validation rules for transaction batches:
     * 1. Process transactions ordered by id.
     * 2. Duplicate transaction ids are invalid.
     * 3. Transactions sharing orderId with any invalid transaction are invalid.
     * 4. BET decreases balance, WIN increases balance.
     * 5. BET that would make balance negative is marked invalid.
     */

    @Override
    public ValidationResult validate(List<Transaction> transactions, long balance) {
        if (transactions == null || transactions.isEmpty() || balance < 0) {
            return ValidationResult.empty();
        }
        if (transactions.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("transactions must not contain null values");
        }

        List<Transaction> sortedTransactions = transactions.stream()
                .sorted()
                .toList();

        Set<Long> duplicateIds = findDuplicateIds(sortedTransactions);
        Set<Long> failedOrderIds = findIntrinsicallyFailedOrders(sortedTransactions, duplicateIds);
        while (true) {
            SimulationResult simulation = simulate(sortedTransactions, balance, failedOrderIds);
            if (simulation.failedOrderId() == null) {
                return new ValidationResult(simulation.statuses());
            }
            if (!failedOrderIds.add(simulation.failedOrderId())) {
                throw new IllegalStateException("validation did not make progress");
            }
        }
    }

    private static Set<Long> findIntrinsicallyFailedOrders(List<Transaction> transactions, Set<Long> duplicateIds) {
        Set<Long> failedOrderIds = new HashSet<>();
        for (Transaction transaction : transactions) {
            if (duplicateIds.contains(transaction.id()) || transaction.amount() < 0 || transaction.type() == null) {
                failedOrderIds.add(transaction.orderId());
            }
        }
        return failedOrderIds;
    }

    private static SimulationResult simulate(List<Transaction> transactions, long initialBalance,
                                             Set<Long> failedOrderIds) {
        long balance = initialBalance;
        List<TransactionStatus> statuses = new ArrayList<>(transactions.size());
        for (Transaction transaction : transactions) {
            if (failedOrderIds.contains(transaction.orderId())) {
                statuses.add(new TransactionStatus(transaction, false));
                continue;
            }
            if (transaction.type() == TransactionType.BET) {
                if (balance < transaction.amount()) {
                    return SimulationResult.failed(transaction.orderId());
                }
                balance -= transaction.amount();
            } else if (Long.MAX_VALUE - balance < transaction.amount()) {
                return SimulationResult.failed(transaction.orderId());
            } else {
                balance += transaction.amount();
            }
            statuses.add(new TransactionStatus(transaction, true));
        }
        return SimulationResult.success(statuses);
    }

    private static Set<Long> findDuplicateIds(List<Transaction> transactions) {
        // Finds all duplicate transaction ids up front so they are excluded from
        // valid results in a single deterministic pass.
        Map<Long, Integer> idCount = new HashMap<>();
        Set<Long> duplicateIds = new HashSet<>();

        for (Transaction transaction : transactions) {
            int occurrences = idCount.getOrDefault(transaction.id(), 0) + 1;
            idCount.put(transaction.id(), occurrences);
            if (occurrences > 1) {
                duplicateIds.add(transaction.id());
            }
        }

        return duplicateIds;
    }

    private record SimulationResult(List<TransactionStatus> statuses, Long failedOrderId) {

        private static SimulationResult success(List<TransactionStatus> statuses) {
            return new SimulationResult(statuses, null);
        }

        private static SimulationResult failed(long orderId) {
            return new SimulationResult(List.of(), orderId);
        }
    }
}
