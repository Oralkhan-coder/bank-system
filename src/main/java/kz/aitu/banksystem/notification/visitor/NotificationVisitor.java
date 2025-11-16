package kz.aitu.banksystem.notification.visitor;

import kz.aitu.banksystem.notification.visitable.impl.EmailNotification;
import kz.aitu.banksystem.notification.visitable.impl.PushNotification;
import kz.aitu.banksystem.notification.visitable.impl.TelegramNotification;

public interface NotificationVisitor {

    void notify(EmailNotification emailNotification);
    void notify(PushNotification pushNotification);
    void notify(TelegramNotification telegramNotification);
}