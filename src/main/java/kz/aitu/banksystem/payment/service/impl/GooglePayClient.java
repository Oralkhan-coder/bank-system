package kz.aitu.banksystem.payment.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class GooglePayClient {

    public void sendPayment(BigDecimal amountInUsd) {
        System.out.println("Google Pay: sending payment of " + amountInUsd + " USD");
    }
}