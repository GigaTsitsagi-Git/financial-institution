package com.solvd.financialinstituion.model;

import com.solvd.financialinstituion.enums.AccountType;
import com.solvd.financialinstituion.enums.CurrencyType;
import com.solvd.financialinstituion.exception.InsufficientFundsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class CheckingAccount extends Account {

    private static final Logger logger = LogManager.getLogger(CheckingAccount.class);

    private BigDecimal overdraftLimit;

    public CheckingAccount(String accountNumber, BigDecimal balance, BigDecimal overdraftLimit) {
        super(accountNumber, AccountType.CHEKGINS, CurrencyType.USD, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public BigDecimal getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(BigDecimal overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public void withdraw(BigDecimal amount) throws InsufficientFundsException {
        if ((getBalance().add(overdraftLimit)).compareTo(amount) < 0) {
            throw new InsufficientFundsException("Not enough money in the account " + getAccountNumber());
        }
        logger.info("Withdraw was success");
    }

    public boolean isOverdraftInUse() {
        return getBalance().compareTo(BigDecimal.ZERO) < 0;
    }

    @Override
    public BigDecimal calculateTotalValue() {
        return getBalance().add(overdraftLimit);
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "accountNumber='" + getAccountNumber() + '\'' +
                ", balance=" + getBalance() +
                ", overdraftLimit=" + overdraftLimit +
                '}';
    }
}
