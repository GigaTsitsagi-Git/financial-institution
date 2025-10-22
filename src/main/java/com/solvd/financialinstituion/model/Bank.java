package com.solvd.financialinstituion.model;

import com.solvd.financialinstituion.employee.Employee;
import com.solvd.financialinstituion.enums.CurrencyType;
import com.solvd.financialinstituion.enums.TransactionStatus;
import com.solvd.financialinstituion.exception.CurrencyNotSupportedException;
import com.solvd.financialinstituion.exception.UnderageCustomerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.financialinstituion.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bank {

    private static final Logger logger = LogManager.getLogger(Bank.class);

    private String name;

    private List<Customer> customers;
    private List<Currency> currencies;
    private List<Transaction> transactions;
    private List<Employee> employees;

    public Bank(String name) {
        customers = new ArrayList<>();
        currencies = new ArrayList<>();
        transactions = new ArrayList<>();
        employees = new ArrayList<>();
        this.name = name;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addCustomer(Customer customer) {
        if (customer.getAge() < 18) {
            throw new UnderageCustomerException("Customer must be at least 18 years old: " + customer.getAge());
        }
        customers.add(customer);
    }

    public void addCurrency(Currency currency) {
        currencies.add(currency);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void printEmployees() {
        if (employees.isEmpty()) {
            logger.info("The list is empty");
        }
        employees.stream()
                .filter(Objects::nonNull)
                .forEach(employee -> logger.info("Employee: {}", employee));
    }

    public final void printCurrencies() {
        currencies.stream()
                .filter(Objects::nonNull)
                .forEach(currency -> logger.info("Currency: {}", currency));
    }

    public void printCustomers() {
        customers.stream()
                .filter(Objects::nonNull)
                .forEach(customer -> logger.info("Customer: {}", customer));
    }

    public void printTransactions() {
        transactions.stream()
                .filter(Objects::nonNull)
                .forEach(transaction -> logger.info("Transaction: {}", transaction));
    }

    public Employee getFirstEmployee() {
        return employees.getFirst();
    }

    public void removeCurrencyByIndex(int index) {
        if (index > currencies.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds Exception");
        }
        currencies.remove(index);
    }

    public Customer getCustomerByIndex(int index) {
        if (index > customers.size()) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        return customers.get(index);
    }

    public void exchangeMoney(String currencyCode) {
        boolean found = false;
        for (Currency c : currencies) {
            if (c != null && c.getCODE().equals(currencyCode)) {
                found = true;
                break;
            }
        }

        if (!found) {
            throw new CurrencyNotSupportedException("Currency not supported " + currencyCode);
        }

        logger.info("Exchanged into {}", currencyCode);
    }

    public String getName() {
        return name;
    }

    // Enhanced methods using enums
    public void addCurrency(CurrencyType currencyType) {
        Currency currency = new Currency(currencyType);
        currencies.add(currency);
    }

    public boolean supportsCurrency(CurrencyType currencyType) {
        return currencies.stream()
                .anyMatch(c -> c.getCurrencyType() == currencyType);
    }

    public List<Transaction> getTransactionsByStatus(TransactionStatus status) {
        return transactions.stream()
                .filter(t -> t.getTransactionStatus() == status)
                .toList();
    }

    public List<Transaction> getPendingTransactions() {
        return getTransactionsByStatus(TransactionStatus.PENDING);
    }

    public List<Transaction> getCompletedTransactions() {
        return getTransactionsByStatus(TransactionStatus.COMPLETED);
    }

    public void completeAllPendingTransactions() {
        transactions.stream()
                .filter(Transaction::isTransactionPending)
                .forEach(Transaction::completeTransaction);
    }

    public String getBankInfo() {
        return String.format("Bank: %s - Customers: %d, Employees: %d, Currencies: %d, Transactions: %d",
                name, customers.size(), employees.size(), currencies.size(), transactions.size());
    }
}
