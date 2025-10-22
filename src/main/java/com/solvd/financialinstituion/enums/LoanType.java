package com.solvd.financialinstituion.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum LoanType {

    PERSONAL(0.05),
    MORTGAGE(0.03);

    private static final Logger logger = LogManager.getLogger(LoanType.class);
    private final double baseRate;

    static {
        logger.info("LoanType enum loaded into memory.");
    }

    LoanType(double baseRate) {
        this.baseRate = baseRate;
    }

    public double calculateSimpleIntereset(double principal, int years) {
        return principal * baseRate * years;
    }

    public double getBaseRate() {
        return baseRate;
    }
}
