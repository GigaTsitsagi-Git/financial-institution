package interfaces.functional;

import model.Customer;

import java.math.BigDecimal;

public interface ILoanEvaluator {

    boolean approve(Customer customer, BigDecimal amount);
}
