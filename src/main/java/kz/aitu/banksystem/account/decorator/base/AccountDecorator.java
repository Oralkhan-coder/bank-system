package kz.aitu.banksystem.account.decorator.base;

public abstract class AccountDecorator implements Account {

    protected final Account wrappee;

    protected AccountDecorator(Account wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public Long getId() {
        return wrappee.getId();
    }

    @Override
    public double getBalance() {
        return wrappee.getBalance();
    }

    @Override
    public void deposit(double amount) {
        wrappee.deposit(amount);
    }

    @Override
    public void withdraw(double amount) {
        wrappee.withdraw(amount);
    }
}
