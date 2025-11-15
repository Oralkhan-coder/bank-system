package kz.aitu.banksystem.payment.adapter;

import java.math.BigDecimal;

import kz.aitu.banksystem.payment.service.BankPaymentService;
import kz.aitu.banksystem.payment.service.impl.CurrencyConversionService;
import kz.aitu.banksystem.payment.service.impl.GooglePayClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GooglePayAdapter implements BankPaymentService {

    private final GooglePayClient googlePayClient;
    private final CurrencyConversionService currencyConversionService;

    /**
     * Bank System calls this method with any supported currency (e.g. KZT).
     * Adapter converts it to USD and delegates to GooglePayClient.sendPayment.
     */
    @Override
    public void processPayment(BigDecimal amount, String currency) {
        BigDecimal amountInUsd = currencyConversionService.convertToUsd(amount, currency);
        googlePayClient.sendPayment(amountInUsd);
    }
}