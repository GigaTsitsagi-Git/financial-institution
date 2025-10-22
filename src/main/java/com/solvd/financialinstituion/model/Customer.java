package com.solvd.financialinstituion.model;

import com.solvd.financialinstituion.interfaces.IMove;
import com.solvd.financialinstituion.interfaces.IStorable;
import com.solvd.financialinstituion.transaction.Loan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Customer implements IMove, IStorable {

    private static final Logger logger = LogManager.getLogger(Customer.class);

    private String customerId;
    private String name;
    private int age;

    private Set<Loan> loans = new HashSet<>();
    private Set<Account> accounts = new HashSet<>();
    private Set<CreditCard> creditCards = new HashSet<>();

    public Customer(String name, String customerId, int age) {
        this.name = name;
        this.customerId = customerId;
        this.age = age;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public void addCreditCard(CreditCard creditCard) {
        creditCards.add(creditCard);
    }

    public Set<Loan> getLoans() {
        return loans;
    }

    public Set<CreditCard> getCreditCards() {
        return creditCards;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public int getAccountCount() {
        return accounts.size();
    }

    public int getLoanCount() {
        return loans.size();
    }

    public void printAccounts() {
        accounts.stream()
                .filter(Objects::nonNull)
                .forEach(account -> logger.info("Account: {}", account));
    }

    public void printLoans() {
        loans.stream()
                .filter(Objects::nonNull)
                .forEach(loan -> logger.info("Loan: {}", loan));
    }

    public Loan getFirstLoan() {
        return loans.iterator().next();
    }

    @Override
    public String toString() {
        return "Customer name: " + name + ", Accounts: " + accounts.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Customer customer = (Customer) obj;
        return customerId != null && name.equals(customer.customerId);
    }

    @Override
    public int hashCode() {
        return customerId != null ? customerId.hashCode() : 0;
    }

    @Override
    public void move() {
        logger.info("Customer Entered the Bank");
    }

    @Override
    public void save() {
        logger.info("Saved the data into Bank DataBase");
    }
}
