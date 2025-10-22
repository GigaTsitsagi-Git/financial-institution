package com.solvd.financialinstituion.document;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Receipt extends Document {

    private static final Logger logger = LogManager.getLogger(Receipt.class);
    
    private String transactionMessage;
    private BigDecimal amount;
    private LocalTime createdTime;

    public Receipt(String id, LocalDate date, String transactionMessage, BigDecimal amount) {
        super(id, date);
        this.transactionMessage = transactionMessage;
        this.amount = amount;
        createdTime = LocalTime.now();
    }

    @Override
    public void printDetails() {
        logger.info("Receipt ID: {}, Date: {}", getId(), getDate());
        logger.info("Transaction: {}, Amount: {}", transactionMessage, amount);
    }

    @Override
    public LocalTime getCreatedTime() {
        return createdTime;
    }
}
