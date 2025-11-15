package kz.aitu.banksystem.account.decorator;

public class Demo {
    public static void main(String[] args) {

        Account acc = new BasicAccount(1L, 1000);

        BonusInterestAccountDecorator decorated = new BonusInterestAccountDecorator(
                new FeeAccountDecorator(
                        new LoggingAccountDecorator(acc), 1.5), 5.0);

        decorated.deposit(500);
        decorated.withdraw(200);

        decorated.applyMonthlyInterest();
    }
}
