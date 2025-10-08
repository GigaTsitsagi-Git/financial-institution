package employee;

import java.math.BigDecimal;

public class Manager extends BankEmployee {

    public Manager(String firstName, String lastName, int age, String employeeId, BigDecimal salary) {
        super(firstName, lastName, age, employeeId, salary);
    }

    @Override
    public void approveLoan(BigDecimal amount) {
        System.out.println("Loan approved: " + amount);
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Manager: salary => " + salary);
    }

    @Override
    public void work() {
        System.out.println("Manager " + getFirstName() + " is holding a team meeting");
    }
}
