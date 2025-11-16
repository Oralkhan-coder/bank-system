package kz.aitu.banksystem.notification.visitor.impl;

import kz.aitu.banksystem.notification.visitable.impl.EmailNotification;
import kz.aitu.banksystem.notification.visitable.impl.PushNotification;
import kz.aitu.banksystem.notification.visitable.impl.TelegramNotification;
import kz.aitu.banksystem.notification.visitor.NotificationVisitor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotificationVisitorImpl implements NotificationVisitor {
    @Override
    public void notify(EmailNotification emailNotification) {
        log.info("Email notification for email {}", emailNotification.getEmail());
        log.info("The content {}", emailNotification.getContent());

    }

    @Override
    public void notify(TelegramNotification telegramNotification) {
        log.info("Telegram notification for number {}", telegramNotification.getPhoneNumber());
        log.info("The content {}", telegramNotification.getContent());
    }

    @Override
    public void notify(PushNotification pushNotification) {
        log.info("Push notification for number {}", pushNotification.getPhoneNumber());
        log.info("The content {}", pushNotification.getContent());
    }
}
