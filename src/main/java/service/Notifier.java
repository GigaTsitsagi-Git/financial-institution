package service;

public class Notifier<T> {

    private T target;
    private String message;

    public Notifier(T target, String message) {
        this.target = target;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Notification for " + target + ": " + message;
    }
}
