package com.solvd.financialinstituion.employee;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class Manager extends BankEmployee {

    private static final Logger logger = LogManager.getLogger(Manager.class);

    public Manager(String firstName, String lastName, int age, String employeeId, BigDecimal salary) {
        super(firstName, lastName, age, employeeId, salary);
    }

    @Override
    public void approveLoan(BigDecimal amount) {
        logger.info("Loan approved: {}", amount);
    }

    @Override
    public void printDetails() {
        super.printDetails();
        logger.info("Manager: salary => {}", salary);
    }

    @Override
    public void work() {
        logger.info("Manager {} is holding a team meeting", getFirstName());
    }
}
