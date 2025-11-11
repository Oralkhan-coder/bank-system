package kz.aitu.banksystem.deposit.strategy;

import org.springframework.stereotype.Component;

@Component
public class FixedDepositStrategy implements InterestCalculationStrategy {
    @Override
    public double calculate(double amount, double rate, int months) {
        return amount * (rate / 100) * (months / 12.0);
    }
}