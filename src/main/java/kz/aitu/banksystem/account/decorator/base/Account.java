package kz.aitu.banksystem.account.decorator.base;

public interface Account {
    Long getId();
    double getBalance();
    void deposit(double amount);
    void withdraw(double amount);
}
