package employee;

import java.math.BigDecimal;

public abstract class Employee extends Person {

    protected String employeeId;
    protected BigDecimal salary;

    public Employee(String firstName, String lastName, int age, String employeeId, BigDecimal salary) {
        super(firstName, lastName, age);
        this.employeeId = employeeId;
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public abstract void work();

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Employee id - " + employeeId);
    }

    @Override
    public String toString() {
        return "Employee id: " + employeeId + "name: " + getFirstName() + " " + getLastName() + " Salary: " + salary;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Employee employee = (Employee) obj;
        return employeeId != null && employeeId.equals(employee.employeeId);
    }

    @Override
    public int hashCode() {
        return employeeId != null ? employeeId.hashCode() : 0;
    }
}
