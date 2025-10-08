package trading;

import model.Account;
import model.Customer;

import java.util.Set;

public class Trader {

    private String traderId;
    private Customer customer;

    public Trader(String traderId, Customer customer) {
        this.traderId = traderId;
        this.customer = customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setTraderId(String traderId) {
        this.traderId = traderId;
    }

    public String getTraderId() {
        return traderId;
    }

    public void showCustomerAccounts() {
        if (customer != null && customer.getAccounts() != null) {
            System.out.println("Accounts for customer " + customer.getName() + ":");
            Set<Account> accaunts = customer.getAccounts();
            for (Account account : accaunts) {
                System.out.println(" - " + account.getAccountNumber() + " : " + account.getBalance());
            }
        } else {
            System.out.println("No customer or accounts found for this trader.");
        }
    }

}
