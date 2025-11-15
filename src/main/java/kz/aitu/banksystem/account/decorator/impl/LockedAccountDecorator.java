package kz.aitu.banksystem.account.decorator.impl;

import kz.aitu.banksystem.account.decorator.base.Account;
import kz.aitu.banksystem.account.decorator.base.AccountDecorator;

public class LockedAccountDecorator extends AccountDecorator {

    public LockedAccountDecorator(Account wrappee) {
        super(wrappee);
    }

    @Override
    public void deposit(double amount) {
        throw new IllegalStateException("Account is locked");
    }

    @Override
    public void withdraw(double amount) {
        throw new IllegalStateException("Account is locked");
    }
}
