package kz.aitu.banksystem.account.decorator.impl;

import kz.aitu.banksystem.account.decorator.base.Account;
import kz.aitu.banksystem.account.decorator.base.AccountDecorator;

public class FeeAccountDecorator extends AccountDecorator {

    private final double feePercent;

    public FeeAccountDecorator(Account wrappee, double feePercent) {
        super(wrappee);
        this.feePercent = feePercent;
    }

    @Override
    public void withdraw(double amount) {
        double fee = amount * feePercent / 100.0;
        double total = amount + fee;
        wrappee.withdraw(total);
    }
}
