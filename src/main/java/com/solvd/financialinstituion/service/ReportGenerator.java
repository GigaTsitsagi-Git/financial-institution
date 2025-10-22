package com.solvd.financialinstituion.service;

import com.solvd.financialinstituion.interfaces.IPrint;
import com.solvd.financialinstituion.model.Account;
import com.solvd.financialinstituion.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class ReportGenerator {

    private static final Logger logger = LogManager.getLogger(ReportGenerator.class);

    public void genererateCustomerReport(Customer customer) {
        logger.info("--- Customer Report for {} ---", customer.getName());
        Set<Account> accounts = customer.getAccounts();
        for (Account account : accounts) {
            logger.info("Account: {} | Balance: {}", account.getAccountNumber(), account.getBalance());
        }
    }

    public void printDetails(IPrint print) {
        logger.info("=== Printing Document ===");
        print.printDetails();  // Polymorphic call
        logger.info("=========================");
    }

}
