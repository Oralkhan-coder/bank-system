package kz.aitu.banksystem.payment.service;

public interface PaymentService {
    void pay(Double amount, String accountNumber);
}