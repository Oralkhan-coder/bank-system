package kz.aitu.banksystem.notification.visitable.impl;

import kz.aitu.banksystem.notification.visitable.Visitable;
import kz.aitu.banksystem.notification.visitor.NotificationVisitor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PushNotification implements Visitable {
    private String phoneNumber;
    private String content;

    @Override
    public void accept(NotificationVisitor visitor) {
        visitor.notify(this);
    }
}
