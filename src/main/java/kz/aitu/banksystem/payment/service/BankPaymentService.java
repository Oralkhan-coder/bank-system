package kz.aitu.banksystem.payment.service;

import java.math.BigDecimal;

public interface BankPaymentService {
    void processPayment(BigDecimal amount, String currency);
}