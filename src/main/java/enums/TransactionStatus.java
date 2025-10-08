package enums;

public enum TransactionStatus {

    PENDING("Waiting for approval"),
    COMPLETED("Transaction successful"),
    FAILED("Transaction failed"),
    CANCELED("Transaction cancelled");

    private final String description;

    static {
        System.out.println("TransactionStatus enum loaded into memory.");
    }

    TransactionStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFinalStatus() {
        return this == COMPLETED || this == FAILED || this == CANCELED;
    }
}
