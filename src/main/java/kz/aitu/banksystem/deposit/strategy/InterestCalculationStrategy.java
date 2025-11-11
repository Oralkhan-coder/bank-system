package kz.aitu.banksystem.deposit.strategy;

public interface InterestCalculationStrategy {
    double calculate(double amount, double rate, int months);
}