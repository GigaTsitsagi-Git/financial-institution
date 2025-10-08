package service;

import interfaces.IPrint;
import model.Account;
import model.Customer;

import java.util.Set;

public class ReportGenerator {

    public void genererateCustomerReport(Customer customer) {
        System.out.println("--- Customer Report for " + customer.getName() + " ---");
        Set<Account> accounts = customer.getAccounts();
        for (Account account : accounts) {
            System.out.println("Account: " + account.getAccountNumber() + " | Balance: " + account.getBalance());

        }
    }

    public void printDetails(IPrint print) {
        System.out.println("=== Printing Document ===");
        print.printDetails();  // Polymorphic call
        System.out.println("=========================");
    }

}
