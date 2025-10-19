package document;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalTime;

public class Contract extends Document {

    private static final Logger logger = LogManager.getLogger(Contract.class);

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
        logger.info("Contract ID: {}, Date: {}", getId(), getDate());
        logger.info("PartyA: {}, PartyB: {}", partyA, partyB);
        createdTime = LocalTime.now();
    }

    @Override
    public LocalTime getCreatedTime() {
        return createdTime;
    }
}
