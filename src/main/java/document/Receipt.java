package document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Receipt extends Document {

    private String transactionMessage;
    private BigDecimal amount;
    private LocalTime createdTime;

    public Receipt(String id, LocalDate date, String transactionMessage, BigDecimal amount) {
        super(id, date);
        this.transactionMessage = transactionMessage;
        this.amount = amount;
        createdTime = LocalTime.now();
    }

    @Override
    public void printDetails() {
        System.out.println("Receipt ID: " + getId() + ", Date: " + getDate());
        System.out.println("Transaction: " + transactionMessage + ", Amount: " + amount);
    }

    @Override
    public LocalTime getCreatedTime() {
        return createdTime;
    }
}
