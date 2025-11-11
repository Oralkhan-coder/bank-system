package kz.aitu.banksystem.account.strategy.impl;

import kz.aitu.banksystem.account.strategy.CurrencyConversionStrategy;
import org.springframework.stereotype.Component;

@Component
public class FixedRateCurrencyConversionImpl implements CurrencyConversionStrategy {

    @Override
    public double convert(double amount, String fromCurrency, String toCurrency) {
        if ("USD".equalsIgnoreCase(fromCurrency) && "EUR".equalsIgnoreCase(toCurrency)) {
            return amount * 0.9;
        } else if ("EUR".equalsIgnoreCase(fromCurrency) && "USD".equalsIgnoreCase(toCurrency)) {
            return amount * 1.1;
        }
        return amount;
    }
}