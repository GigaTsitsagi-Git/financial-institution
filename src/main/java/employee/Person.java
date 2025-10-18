package employee;

import interfaces.IMove;
import interfaces.IPrint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Person implements IMove, IPrint {

    private static final Logger logger = LogManager.getLogger(Person.class);
    
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
        logger.info("Person is Walking");
    }

    @Override
    public void printDetails() {
        logger.info("First name - {}, Last name - {}", firstName, lastName);
    }
}
