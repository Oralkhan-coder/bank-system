package kz.aitu.banksystem.account.strategy;

public interface CurrencyConversionStrategy {
    double convert(double amount, String fromCurrency, String toCurrency);
}