package com.solvd.financialinstituion.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class ValidationUtils {
    
    private static final Logger logger = LogManager.getLogger(ValidationUtils.class);
    
    public static boolean isValidAmount(BigDecimal amount) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }
    
    public static boolean isValidAccountNumber(String accountNumber) {
        return accountNumber != null && accountNumber.length() >= 8;
    }
    
    public static boolean isValidCustomerId(String customerId) {
        return customerId != null && customerId.startsWith("C") && customerId.length() == 4;
    }
    
    public static void logValidation(String field, boolean isValid) {
        logger.info("Validation for {}: {}", field, isValid ? "PASSED" : "FAILED");
    }
}
