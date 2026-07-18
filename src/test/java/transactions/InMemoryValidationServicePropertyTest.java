package transactions;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.Tag;
import net.jqwik.api.constraints.LongRange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("property")
class InMemoryValidationServicePropertyTest {
    private final ValidationService service = ValidationService.getInstance();

    @Property(tries = 250)
    void validationIsIndependentOfInputOrder(
            @ForAll("transactionBatches") List<Transaction> transactions,
            @ForAll @LongRange(min = 0, max = 1_000_000) long balance) {
        List<Transaction> reversed = new ArrayList<>(transactions);
        Collections.reverse(reversed);

        List<TransactionStatus> directResult = service.validate(transactions, balance).getTransactions();
        List<TransactionStatus> reversedResult = service.validate(reversed, balance).getTransactions();

        assertEquals(directResult, reversedResult);
        assertEquals(transactions.size(), directResult.size());
    }

    @Property(tries = 250)
    void everyOrderHasOneConsistentStatus(
            @ForAll("transactionBatches") List<Transaction> transactions,
            @ForAll @LongRange(min = 0, max = 1_000_000) long balance) {
        List<TransactionStatus> statuses = service.validate(transactions, balance).getTransactions();
        Map<Long, Boolean> statusByOrder = new HashMap<>();

        for (int index = 0; index < statuses.size(); index++) {
            TransactionStatus current = statuses.get(index);
            if (index > 0) {
                assertTrue(statuses.get(index - 1).transaction().id() < current.transaction().id());
            }
            Boolean existing = statusByOrder.putIfAbsent(current.transaction().orderId(), current.status());
            if (existing != null) {
                assertEquals(existing, current.status());
            }
        }
    }

    @Property(tries = 150)
    void nonOverflowingWinsAreAlwaysAccepted(
            @ForAll("winOnlyBatches") List<Transaction> transactions,
            @ForAll @LongRange(min = 0, max = 1_000_000) long balance) {
        List<TransactionStatus> statuses = service.validate(transactions, balance).getTransactions();

        assertEquals(transactions.size(), statuses.size());
        assertTrue(statuses.stream().allMatch(TransactionStatus::status));
    }

    @Provide
    Arbitrary<List<Transaction>> transactionBatches() {
        Arbitrary<TransactionSeed> seed = Combinators.combine(
                Arbitraries.longs().between(0, 10),
                Arbitraries.longs().between(0, 10_000),
                Arbitraries.of(TransactionType.values())
        ).as(TransactionSeed::new);

        return seed.list().ofMaxSize(30)
                .map(InMemoryValidationServicePropertyTest::toTransactions);
    }

    @Provide
    Arbitrary<List<Transaction>> winOnlyBatches() {
        return Arbitraries.longs().between(0, 10_000)
                .list().ofMaxSize(30)
                .map(amounts -> {
                    List<Transaction> transactions = new ArrayList<>(amounts.size());
                    for (int index = 0; index < amounts.size(); index++) {
                        transactions.add(new Transaction(index + 1L, index % 7L, amounts.get(index), TransactionType.WIN));
                    }
                    return transactions;
                });
    }

    private static List<Transaction> toTransactions(List<TransactionSeed> seeds) {
        List<Transaction> transactions = new ArrayList<>(seeds.size());
        for (int index = 0; index < seeds.size(); index++) {
            TransactionSeed seed = seeds.get(index);
            transactions.add(new Transaction(index + 1L, seed.orderId(), seed.amount(), seed.type()));
        }
        return transactions;
    }

    private record TransactionSeed(long orderId, long amount, TransactionType type) {
    }
}
