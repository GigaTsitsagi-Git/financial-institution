package trading;

import model.Account;
import model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class Trader {

    private static final Logger logger = LogManager.getLogger(Trader.class);
    
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
            logger.info("Accounts for customer {}:", customer.getName());
            Set<Account> accaunts = customer.getAccounts();
            for (Account account : accaunts) {
                logger.info(" - {} : {}", account.getAccountNumber(), account.getBalance());
            }
        } else {
            logger.info("No customer or accounts found for this trader.");
        }
    }

}
