package transactions;

import lombok.Getter;

import java.util.List;

@Getter
final class ValidationResult {
    private final List<TransactionStatus> transactions;

    public ValidationResult(List<TransactionStatus> transactions) {
        this.transactions = List.copyOf(transactions);
    }

    public static ValidationResult empty() {
        return new ValidationResult(List.of());
    }
}
