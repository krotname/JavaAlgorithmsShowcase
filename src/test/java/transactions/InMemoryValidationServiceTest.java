package transactions;

import org.junit.jupiter.api.Tag;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration-style tests for transaction validation pipeline.
 */
@Tag("integration")
class InMemoryValidationServiceTest {
    private final ValidationService service = ValidationService.getInstance();

    private Transaction createTransaction(long id, long orderId, long amount, TransactionType type) {
        return new Transaction(id, orderId, amount, type);
    }

    @Test
    void shouldApproveValidBetAndWinFlow() {
        List<Transaction> transactions = List.of(
                createTransaction(1, 1, 50, TransactionType.BET),
                createTransaction(2, 2, 40, TransactionType.WIN)
        );
        ValidationResult actual = service.validate(transactions, 50);

        assertEquals(2, actual.getTransactions().size());
        assertTrue(actual.getTransactions().get(0).status());
        assertTrue(actual.getTransactions().get(1).status());
    }

    @Test
    void shouldRejectBetWhenBalanceNotEnough() {
        List<Transaction> transactions = List.of(
                createTransaction(1, 1, 90, TransactionType.BET),
                createTransaction(2, 2, 10, TransactionType.WIN)
        );

        ValidationResult actual = service.validate(transactions, 50);
        assertEquals(2, actual.getTransactions().size());
        assertFalse(actual.getTransactions().get(0).status());
        assertTrue(actual.getTransactions().get(1).status());
    }

    @Test
    void shouldRejectDuplicatedIdsAndCascadeByOrder() {
        List<Transaction> transactions = new ArrayList<>(List.of(
                createTransaction(1, 1, 50, TransactionType.BET),
                createTransaction(1, 2, 40, TransactionType.WIN)
        ));
        transactions.add(createTransaction(3, 1, 5, TransactionType.WIN));
        transactions.add(createTransaction(4, 3, 5, TransactionType.WIN));

        ValidationResult actual = service.validate(transactions, 100);

        assertFalse(actual.getTransactions().get(0).status());
        assertFalse(actual.getTransactions().get(1).status());
        assertFalse(actual.getTransactions().get(2).status());
        assertTrue(actual.getTransactions().get(3).status());
    }

    @Test
    void shouldProcessTransactionsByIdWhenInputIsUnordered() {
        List<Transaction> transactions = List.of(
                createTransaction(3, 10, 50, TransactionType.WIN),
                createTransaction(1, 10, 90, TransactionType.BET),
                createTransaction(2, 11, 20, TransactionType.BET)
        );

        ValidationResult actual = service.validate(transactions, 100);

        assertEquals(1, actual.getTransactions().get(0).transaction().id());
        assertEquals(2, actual.getTransactions().get(1).transaction().id());
        assertEquals(3, actual.getTransactions().get(2).transaction().id());
        assertTrue(actual.getTransactions().get(0).status());
        assertFalse(actual.getTransactions().get(1).status());
        assertTrue(actual.getTransactions().get(2).status());
    }

    @Test
    void shouldRejectInvalidAmountsAndBalanceOverflowAndCascadeByOrder() {
        List<Transaction> transactions = List.of(
                createTransaction(1, 10, -1, TransactionType.BET),
                createTransaction(2, 10, 1, TransactionType.WIN),
                createTransaction(3, 20, 1, TransactionType.WIN)
        );

        ValidationResult invalidAmount = service.validate(transactions, 10);
        assertFalse(invalidAmount.getTransactions().get(0).status());
        assertFalse(invalidAmount.getTransactions().get(1).status());
        assertTrue(invalidAmount.getTransactions().get(2).status());

        ValidationResult overflow = service.validate(
                List.of(createTransaction(1, 30, 1, TransactionType.WIN)),
                Long.MAX_VALUE
        );
        assertFalse(overflow.getTransactions().get(0).status());
    }

    @Test
    void shouldInvalidateEarlierTransactionsAndRemoveTheirBalanceEffectsWhenOrderFailsLater() {
        ValidationResult intrinsicFailure = service.validate(List.of(
                createTransaction(1, 10, 50, TransactionType.BET),
                createTransaction(2, 20, 50, TransactionType.BET),
                createTransaction(3, 10, -1, TransactionType.WIN)
        ), 50);

        assertFalse(intrinsicFailure.getTransactions().get(0).status());
        assertTrue(intrinsicFailure.getTransactions().get(1).status());
        assertFalse(intrinsicFailure.getTransactions().get(2).status());

        ValidationResult balanceFailure = service.validate(List.of(
                createTransaction(1, 30, 80, TransactionType.BET),
                createTransaction(2, 40, 10, TransactionType.WIN),
                createTransaction(3, 30, 31, TransactionType.BET)
        ), 100);

        assertFalse(balanceFailure.getTransactions().get(0).status());
        assertTrue(balanceFailure.getTransactions().get(1).status());
        assertFalse(balanceFailure.getTransactions().get(2).status());
    }

    @Test
    void shouldRejectNullEntriesAndExposeAnImmutableResult() {
        List<Transaction> withNull = new ArrayList<>();
        withNull.add(null);
        assertThrows(IllegalArgumentException.class, () -> service.validate(withNull, 0));

        ValidationResult result = service.validate(
                List.of(createTransaction(1, 1, 0, TransactionType.BET)),
                0
        );
        assertThrows(UnsupportedOperationException.class, () -> result.getTransactions().clear());
    }
}
