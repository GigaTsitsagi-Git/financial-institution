package service;

public class AccountPrinter<T> {

    private T account;

    public AccountPrinter(T account) {
        this.account = account;
    }

    public void printAccount() {
        System.out.println("Account info: " + account);
    }

}
