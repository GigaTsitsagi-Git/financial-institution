package transaction;

import enums.TransactionStatus;
import interfaces.ITransferMoney;
import model.Account;

import java.math.BigDecimal;

public class Transaction implements ITransferMoney {

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
            System.out.println("Transfer completed successfully. Message:" + message);
        } else {
            System.out.println("Failed. Insufficient funds");
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
        System.out.println("| TRANSACTION | Money Transferred:" + amount);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "From: " + from.getAccountNumber() + ", to" + to.getAccountNumber() +
                "| amount: " + amount + "| Message" + message;
    }
}
