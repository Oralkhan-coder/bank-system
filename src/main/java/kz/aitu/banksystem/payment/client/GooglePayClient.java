package kz.aitu.banksystem.payment.client;

import java.math.BigDecimal;

public interface GooglePayClient {

    public void payByGoogle(Double amount, String phoneNumber);
}