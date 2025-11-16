package kz.aitu.banksystem.notification.strategy.impl;

import kz.aitu.banksystem.notification.strategy.NotificationStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TelegramNotification implements NotificationStrategy {
    p

    @Override
    public void sendOtp(String receiver, String code) {
        System.out.println("Sending Otp by telegram");
        log.info("Sending Otp by telegram {}", code);
    }
}
