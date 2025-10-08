package model;

import java.time.LocalDate;

public class CreditCard {

    private String cardNumber;
    private LocalDate expirationDate;
    private String cvc;

    public CreditCard(String cardNumber, LocalDate expirationDate, String cvc) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getCvc() {
        return cvc;
    }
}
