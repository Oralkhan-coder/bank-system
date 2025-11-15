package kz.aitu.banksystem.notification.visitor.impl;

import kz.aitu.banksystem.account.model.entity.AccountEntity;
import kz.aitu.banksystem.credit.model.entity.CreditEntity;
import kz.aitu.banksystem.deposit.model.entity.DepositEntity;
import kz.aitu.banksystem.notification.visitor.NotificationVisitor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Push notification visitor.
 * Handles sending push notifications for mobile apps.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class PushNotificationVisitor implements NotificationVisitor {

    @Override
    public void visit(AccountEntity account) {
        sendPushNotification(
                account.getUserId(),
                "Account Updated",
                "Your account balance is now " + account.getBalance() + " " + account.getCurrency(),
                "account_update"
        );
    }

    @Override
    public void visit(CreditEntity credit) {
        sendPushNotification(
                credit.getUserId(),
                "Credit Status Change",
                "Your " + credit.getCreditType() + " credit is now " + credit.getStatus(),
                "credit_update"
        );
    }

    @Override
    public void visit(DepositEntity deposit) {
        sendPushNotification(
                deposit.getUserId(),
                "Deposit Interest Earned",
                "Your " + deposit.getType() + " deposit earned interest at " + deposit.getInterestRate() + "%",
                "deposit_update"
        );
    }

    private void sendPushNotification(Long userId, String title, String body, String actionType) {
        log.info("Sending push notification to user: {} - Title: {}", userId, title);
        // TODO: Integrate with Firebase Cloud Messaging (FCM), OneSignal, or other push service
        System.out.println("PUSH [User:" + userId + "] TITLE: " + title + " | BODY: " + body + " | ACTION: " + actionType);
    }
}