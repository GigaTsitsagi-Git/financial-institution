package model;

import enums.AccountType;
import enums.CurrencyType;
import exception.InvalidAccountException;
import interfaces.ICalculable;
import interfaces.IStorable;
import interfaces.functional.IAccountOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class Account implements ICalculable, IStorable {

    private static final Logger logger = LogManager.getLogger(Account.class);

    private String accountNumber;
    private BigDecimal balance;
    private AccountType type;
    private CurrencyType currency;

    public Account(String accountNumber, AccountType type, CurrencyType currency, BigDecimal balance) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new InvalidAccountException("Account number cannot be empty");
        }
        this.type = type;
        this.currency = currency;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void executeOperation(BigDecimal amount, IAccountOperation operation) {
        logger.info("Processing operation on account {}", accountNumber);
        BigDecimal newBalance = operation.apply(balance, amount);
        balance = newBalance;
        logger.info("Operation complete. New balance: {}", balance);
    }

    @Override
    public String toString() {
        return "Account number: " + accountNumber + ", Balance: " + balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Account account = (Account) obj;
        return accountNumber != null && accountNumber.equals(account.accountNumber);
    }

    @Override
    public int hashCode() {
        return accountNumber != null ? accountNumber.hashCode() : 0;
    }

    @Override
    public BigDecimal calculateTotalValue() {
        return balance;
    }

    @Override
    public void save() {
        logger.info("Saved to some Database");
    }

    // Getter methods for enums
    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    // Enhanced methods using enums
    public double calculateInterestForType() {
        return type.calculateInterest(balance.doubleValue());
    }

    public String getFormattedBalance() {
        return currency.formatAmount(balance);
    }

    public String getAccountInfo() {
        return String.format("Account %s [%s] - Balance: %s",
                accountNumber, type.name(), getFormattedBalance());
    }
}
