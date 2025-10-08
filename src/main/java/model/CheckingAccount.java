package model;

import enums.AccountType;
import enums.CurrencyType;
import exception.InsufficientFundsException;

import java.math.BigDecimal;

public class CheckingAccount extends Account {

    private BigDecimal overdraftLimit;

    public CheckingAccount(String accountNumber, BigDecimal balance, BigDecimal overdraftLimit) {
        super(accountNumber, AccountType.BUSINESS, CurrencyType.USD, balance);
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
        System.out.println("Withdraw was success");
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
