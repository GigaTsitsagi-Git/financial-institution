package transaction;

import enums.TransactionStatus;
import interfaces.ITransferMoney;
import model.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class Transaction implements ITransferMoney {

    private static final Logger logger = LogManager.getLogger(Transaction.class);

    private String message;
    private Account from;
    private Account to;
    private BigDecimal amount;
    private TransactionStatus transactionStatus;

    public Transaction(Account from, Account to, BigDecimal amount, String message) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.message = message;
        this.transactionStatus = TransactionStatus.PENDING; // default value
        if (this.from.getBalance().compareTo(amount) > 0) {
            this.from.setBalance(this.from.getBalance().subtract(amount));
            this.to.setBalance(this.to.getBalance().add(amount));
            logger.info("Transfer completed successfully. Message: {}", message);
        } else {
            logger.warn("Failed. Insufficient funds");
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void fromThisAccount(Account account) {
        from = account;
    }

    public void toThisAccount(Account account) {
        to = account;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public void printMoneyTransferred() {
        logger.info("| TRANSACTION | Money Transferred: {}", amount);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "From: " + from.getAccountNumber() + ", to" + to.getAccountNumber() +
                "| amount: " + amount + "| Message" + message;
    }

    // Enhanced methods using TransactionStatus enum
    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus status) {
        this.transactionStatus = status;
    }

    public void completeTransaction() {
        this.transactionStatus = TransactionStatus.COMPLETED;
    }

    public void failTransaction() {
        this.transactionStatus = TransactionStatus.FAILED;
    }

    public void cancelTransaction() {
        this.transactionStatus = TransactionStatus.CANCELED;
    }

    public boolean isTransactionComplete() {
        return transactionStatus == TransactionStatus.COMPLETED;
    }

    public boolean isTransactionPending() {
        return transactionStatus == TransactionStatus.PENDING;
    }

    public String getTransactionInfo() {
        return String.format("Transaction [%s] - Amount: %s, Status: %s, Message: %s",
                transactionStatus.name(), amount, transactionStatus.getDescription(), message);
    }

    public boolean isFinalStatus() {
        return transactionStatus.isFinalStatus();
    }
}
