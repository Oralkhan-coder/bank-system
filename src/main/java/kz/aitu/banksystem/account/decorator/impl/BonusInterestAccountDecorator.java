package kz.aitu.banksystem.account.decorator.impl;

import kz.aitu.banksystem.account.decorator.base.Account;
import kz.aitu.banksystem.account.decorator.base.AccountDecorator;

public class BonusInterestAccountDecorator extends AccountDecorator {

    private final double annualRate;

    public BonusInterestAccountDecorator(Account wrappee, double annualRate) {
        super(wrappee);
        this.annualRate = annualRate;
    }

    public void applyMonthlyInterest() {
        double monthlyRate = annualRate / 12.0 / 100.0;
        double interest = wrappee.getBalance() * monthlyRate;
        wrappee.deposit(interest);
    }
}
