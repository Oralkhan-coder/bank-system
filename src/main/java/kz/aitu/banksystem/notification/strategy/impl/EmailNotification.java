package kz.aitu.banksystem.notification.strategy.impl;

import kz.aitu.banksystem.notification.strategy.NotificationStrategy;

public class EmailNotification implements NotificationStrategy {
    @Override
    public void sendOtp(String receiver, String code) {
        System.out.println("Sending Otp by email");
    }
}
