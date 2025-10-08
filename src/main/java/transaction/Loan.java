package transaction;

import enums.LoanType;
import enums.RiskLevel;
import interfaces.ITransferMoney;
import model.Customer;

import java.math.BigDecimal;

public class Loan implements ITransferMoney {

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
        System.out.println("| LOAN | Money Transferred:" + amount);
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
}
