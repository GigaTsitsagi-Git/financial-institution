package transaction;

import enums.LoanType;
import enums.RiskLevel;
import interfaces.ITransferMoney;
import model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class Loan implements ITransferMoney {

    private static final Logger logger = LogManager.getLogger(Loan.class);

    private BigDecimal amount;
    private BigDecimal interestRate;
    private RiskLevel risk;
    private LoanType type;

    public Loan(Customer borrower, BigDecimal amount, BigDecimal interestRate, LoanType type, RiskLevel risk) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.type = type;
        this.risk = risk;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public void printMoneyTransferred() {
        logger.info("| LOAN | Money Transferred: {}", amount);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public LoanType getType() {
        return type;
    }

    public void setType(LoanType type) {
        this.type = type;
    }

    public RiskLevel getRisk() {
        return risk;
    }

    public void setRisk(RiskLevel risk) {
        this.risk = risk;
    }

    // Enhanced methods using enums
    public double calculateTotalInterest(int years) {
        return type.calculateSimpleIntereset(amount.doubleValue(), years);
    }

    public double getAdjustedInterestRate() {
        return risk.adjustLoanInterest(type.getBaseRate());
    }

    public String getLoanInfo() {
        return String.format("Loan [%s] - Amount: %s, Risk: %s, Type: %s",
                type.name(), amount, risk.getDescription(), type.name());
    }

    public boolean isHighRiskLoan() {
        return risk == RiskLevel.HIGH;
    }

    public double getRiskPenalty() {
        return risk.getPenaltyRate();
    }
}
