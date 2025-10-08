package document;

import java.time.LocalDate;
import java.time.LocalTime;

public class Contract extends Document {

    private String partyA;
    private String partyB;
    private LocalTime createdTime;

    public Contract(String id, LocalDate date, String partyA, String partyB) {
        super(id, date);
        this.partyA = partyA;
        this.partyB = partyB;
    }

    @Override
    public void printDetails() {
        System.out.println("Contract ID: " + getId() + ", Date: " + getDate());
        System.out.println("PartyA:" + partyA + ", PartyB: " + partyB);
        createdTime = LocalTime.now();
    }

    @Override
    public LocalTime getCreatedTime() {
        return createdTime;
    }
}
