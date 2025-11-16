package kz.aitu.banksystem.notification.visitable.impl;

import kz.aitu.banksystem.notification.visitable.Visitable;
import kz.aitu.banksystem.notification.visitor.NotificationVisitor;
import lombok.Data;

@Data
public class EmailNotification implements Visitable {
    private String email;
    private String content;

    @Override
    public void accept(NotificationVisitor visitor) {
        visitor.notify(this);
    }
}
