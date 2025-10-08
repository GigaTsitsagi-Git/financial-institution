package employee;

import interfaces.IMove;
import interfaces.IPrint;

public class Person implements IMove, IPrint {

    protected String firstName;
    protected String lastName;
    protected int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void move() {
        System.out.println("Person is Walking");
    }

    @Override
    public void printDetails() {
        System.out.println("First name - " + firstName + ", Last name - " + lastName);
    }
}
