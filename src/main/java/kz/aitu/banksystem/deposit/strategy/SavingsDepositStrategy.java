package kz.aitu.banksystem.deposit.strategy;

import org.springframework.stereotype.Component;

@Component
public class SavingsDepositStrategy implements InterestCalculationStrategy {
    @Override
    public double calculate(double amount, double rate, int months) {
        double interest = amount * (rate / 100) * (months / 12.0);
        return amount + interest - (interest * 0.05); // 5% штраф.
    }
}