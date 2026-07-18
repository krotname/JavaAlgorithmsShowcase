package transactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

record ValidationResult(List<TransactionStatus> transactions) {
    ValidationResult {
        transactions = List.copyOf(transactions);
    }

    public static ValidationResult empty() {
        return new ValidationResult(List.of());
    }

    public List<TransactionStatus> getTransactions() {
        return Collections.unmodifiableList(new ArrayList<>(transactions));
    }
}
