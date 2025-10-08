package enums;

public enum RiskLevel {

    LOW("Safe", 0.01),
    MEDIUM("Balanced", 0.05),
    HIGH("Not Safe", 0.1);

    private final String description;
    private final double penaltyRate;

    static {
        System.out.println("RiskLevel enum loaded into memory");
    }

    RiskLevel(String description, double penaltyRate) {
        this.description = description;
        this.penaltyRate = penaltyRate;
    }

    public String getDescription() {
        return description;
    }

    public double getPenaltyRate() {
        return penaltyRate;
    }

    public double adjustLoanInterest(double baseRate) {
        return baseRate + penaltyRate;
    }
}
