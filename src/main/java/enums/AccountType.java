package enums;

public enum AccountType {

    CHEKGINS(0.01),
    SAVINGS(0.03),
    BUSINESS(0.02);

    private final double interestRate;

    static {
        System.out.println("AccountType enum loaded into memory");
    }

    AccountType(double rate) {
        this.interestRate = rate;
    }

    public double calculateInterest(double balance) {
        return balance * interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
