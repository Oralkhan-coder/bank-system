package kz.aitu.banksystem.notification.visitable;

import kz.aitu.banksystem.notification.visitor.NotificationVisitor;

public interface Visitable {
    void accept(NotificationVisitor visitor);
}