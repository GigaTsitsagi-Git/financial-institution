package enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum RiskLevel {

    LOW("Safe", 0.01),
    MEDIUM("Balanced", 0.05),
    HIGH("Not Safe", 0.1);

    private static final Logger logger = LogManager.getLogger(RiskLevel.class);
    private final String description;
    private final double penaltyRate;

    static {
        logger.info("RiskLevel enum loaded into memory");
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
