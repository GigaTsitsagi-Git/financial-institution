package enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum TransactionStatus {

    PENDING("Waiting for approval"),
    COMPLETED("Transaction successful"),
    FAILED("Transaction failed"),
    CANCELED("Transaction cancelled");

    private static final Logger logger = LogManager.getLogger(TransactionStatus.class);
    private final String description;

    static {
        logger.info("TransactionStatus enum loaded into memory.");
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
