package document;

import interfaces.ITimestamped;

import java.time.LocalDate;

public abstract class Document implements ITimestamped {

    private String id;
    private LocalDate date;

    public Document(String id, LocalDate date) {
        this.id = id;
        this.date = date;
    }

    protected String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    protected LocalDate getDate() {
        return date;
    }

    protected void setDate(LocalDate date) {
        this.date = date;
    }

    public abstract void printDetails();

    @Override
    public String toString() {
        return "Document id :" + id + "date=" + date;
    }
}
