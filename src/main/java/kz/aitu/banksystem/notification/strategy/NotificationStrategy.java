package kz.aitu.banksystem.notification.strategy;

public interface NotificationStrategy {
    void sendOtp(String receiver, String code);
}
