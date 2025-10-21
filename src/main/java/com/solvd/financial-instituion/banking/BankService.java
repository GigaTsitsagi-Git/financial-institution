package com.solvd.financial_instituion.banking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class BankService {
    
    private static final Logger logger = LogManager.getLogger(BankService.class);
    
    public void processLoan(String customerId, BigDecimal amount) {
        logger.info("Processing loan for customer {} with amount {}", customerId, amount);
    }
    
    public void openAccount(String customerId, String accountType) {
        logger.info("Opening {} account for customer {}", accountType, customerId);
    }
    
    public void closeAccount(String accountId) {
        logger.info("Closing account {}", accountId);
    }
}
