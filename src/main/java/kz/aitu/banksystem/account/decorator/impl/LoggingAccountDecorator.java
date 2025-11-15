package kz.aitu.banksystem.account.decorator.impl;

import kz.aitu.banksystem.account.decorator.base.Account;
import kz.aitu.banksystem.account.decorator.base.AccountDecorator;

public class LoggingAccountDecorator extends AccountDecorator {

    public LoggingAccountDecorator(Account wrappee) {
        super(wrappee);
    }

    @Override
    public void deposit(double amount) {
        System.out.println("Deposit: " + amount);
        wrappee.deposit(amount);
        System.out.println("Balance: " + wrappee.getBalance());
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Withdraw: " + amount);
        wrappee.withdraw(amount);
        System.out.println("Balance: " + wrappee.getBalance());
    }
}
