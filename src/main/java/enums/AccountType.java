package enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum AccountType {

    CHEKGINS(0.01),
    SAVINGS(0.03),
    BUSINESS(0.02);

    private static final Logger logger = LogManager.getLogger(AccountType.class);
    private final double interestRate;

    static {
        logger.info("AccountType enum loaded into memory");
    }

    AccountType(double rate) {
        this.interestRate = rate;
    }

    public double calculateInterest(double balance) {
        return balance * interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
