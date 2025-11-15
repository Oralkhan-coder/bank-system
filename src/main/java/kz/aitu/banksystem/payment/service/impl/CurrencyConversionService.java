package kz.aitu.banksystem.payment.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionService {

    private static final String USD = "USD";

    public BigDecimal convertToUsd(BigDecimal amount, String fromCurrency) {
        if (USD.equalsIgnoreCase(fromCurrency)) {
            return amount;
        }

        if ("KZT".equalsIgnoreCase(fromCurrency)) {
            return amount
                    .divide(BigDecimal.valueOf(480), 2, RoundingMode.HALF_UP);
        }

        throw new IllegalArgumentException("Unsupported currency: " + fromCurrency);
    }
}