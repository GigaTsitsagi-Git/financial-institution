package com.solvd.financialinstituion.concurrent;

import com.solvd.financialinstituion.model.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class AccountDb {

    private static final Logger logger = LogManager.getLogger(AccountDb.class);
    private final Map<String, Account> accounts = new HashMap<>();
    private final String connectionId;

    public AccountDb(String connectionId) {
        this.connectionId = connectionId;
        logger.info("Connection created: {}", connectionId);
    }

    public boolean create(Account account) {
        try {
            Thread.sleep(100);
            accounts.put(account.getAccountNumber(), account);
            logger.info("Created account {}", account.getAccountNumber());
            return true;
        } catch (InterruptedException e) {
            logger.error("Create interrupted");
            return false;
        }
    }

    public Account get(String accountNumber) {
        try {
            Thread.sleep(50);
            return accounts.get(accountNumber);
        } catch (InterruptedException e) {
            logger.error("Get interrupted");
            return null;
        }
    }

    public boolean update(Account account) {
        try {
            Thread.sleep(150);
            accounts.put(account.getAccountNumber(), account);
            logger.info("Updated account {}", account.getAccountNumber());
            return true;
        } catch (InterruptedException e) {
            logger.error("Update interrupted");
            return false;
        }
    }

    public boolean delete(String accountNumber) {
        try {
            Thread.sleep(75);
            accounts.remove(accountNumber);
            logger.info("Deleted account {}", accountNumber);
            return true;
        } catch (InterruptedException e) {
            logger.error("Delete interrupted");
            return false;
        }
    }

    public String getConnectionId() {
        return connectionId;
    }
}
