package kz.aitu.banksystem.notification.context;

import kz.aitu.banksystem.notification.strategy.NotificationStrategy;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotificationContext {
    private NotificationStrategy strategy;

    public NotificationContext(NotificationStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(NotificationStrategy strategy) {
        this.strategy = strategy;
    }

    public void send(String receiver, String code) {
        this.strategy.sendOtp(receiver, code);
    }
}
