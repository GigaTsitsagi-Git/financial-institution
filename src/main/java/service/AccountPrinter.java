package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountPrinter<T> {

    private static final Logger logger = LogManager.getLogger(AccountPrinter.class);
    
    private T account;

    public AccountPrinter(T account) {
        this.account = account;
    }

    public void printAccount() {
        logger.info("Account info: {}", account);
    }

}
