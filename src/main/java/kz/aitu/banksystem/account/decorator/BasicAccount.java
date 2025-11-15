package kz.aitu.banksystem.account.decorator;

public class BasicAccount implements Account {

    private final Long id;
    private double balance;

    public BasicAccount(Long id, double initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalStateException("Not enough funds");
        }
        balance -= amount;
    }
}
