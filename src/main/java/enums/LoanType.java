package enums;

public enum LoanType {

    PERSONAL(0.05),
    MORTGAGE(0.03);

    private final double baseRate;

    static {
        System.out.println("LoanType enum loaded into memory.");
    }

    LoanType(double baseRate) {
        this.baseRate = baseRate;
    }

    public double calculateSimpleIntereset(double principal, int years) {
        return principal * baseRate * years;
    }

    public double getBaseRate() {
        return baseRate;
    }
}
