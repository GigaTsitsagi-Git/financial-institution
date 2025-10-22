package com.solvd.financialinstituion.employee;

import com.solvd.financialinstituion.exception.UnauthorizedActionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class BankEmployee extends Employee {

    private static final Logger logger = LogManager.getLogger(BankEmployee.class);

    public BankEmployee(String firstName, String lastName, int age, String employeeId, BigDecimal salary) {
        super(firstName, lastName, age, employeeId, salary);
    }

    public void approveLoan(BigDecimal amount) {
        if (amount.compareTo(new BigDecimal("5000")) > 0) {
            throw new UnauthorizedActionException("Only Manager can approve loans more than 5000");
        }
        logger.info("Loan approved: {}", amount);
    }

    @Override
    public void printDetails() {
        super.printDetails();
        logger.info("Bank Employee");
    }

    @Override
    public void work() {
        logger.info("Bank employee {} is serving customers at the bank counter", getFirstName());
    }
}
