package employee;

import exception.UnauthorizedActionException;

import java.math.BigDecimal;

public class BankEmployee extends Employee {

    public BankEmployee(String firstName, String lastName, int age, String employeeId, BigDecimal salary) {
        super(firstName, lastName, age, employeeId, salary);
    }

    public void approveLoan(BigDecimal amount) {
        if (amount.compareTo(new BigDecimal("5000")) > 0) {
            throw new UnauthorizedActionException("Only Manager can approve loans more than 5000");
        }
        System.out.println("Loan approved: " + amount);
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Bank Employee");
    }

    @Override
    public void work() {
        System.out.println("Bank employee " + getFirstName() + " is serving customers at the bank counter");
    }
}
