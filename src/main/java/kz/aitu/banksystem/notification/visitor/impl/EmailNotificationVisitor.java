package kz.aitu.banksystem.notification.visitor.impl;

import kz.aitu.banksystem.account.model.entity.AccountEntity;
import kz.aitu.banksystem.credit.model.entity.CreditEntity;
import kz.aitu.banksystem.deposit.model.entity.DepositEntity;
import kz.aitu.banksystem.notification.visitor.NotificationVisitor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Email notification visitor.
 * Handles sending email notifications for different financial entities.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class EmailNotificationVisitor implements NotificationVisitor {

    @Override
    public void visit(AccountEntity account) {
        String email = getUserEmailFromAccount(account);
        String subject = "Account Activity Notification";
        String body = String.format(
                "Your account %s has been updated. Current balance: %s %s",
                account.getAccountNumber(),
                account.getBalance(),
                account.getCurrency()
        );
        sendEmail(email, subject, body);
    }

    @Override
    public void visit(CreditEntity credit) {
        String email = getUserEmailFromCredit(credit);
        String subject = "Credit Notification - " + credit.getCreditType();
        String body = String.format(
                "Your credit status updated: %s. Remaining amount: %s. Status: %s",
                credit.getCreditType(),
                credit.getRemainingAmount(),
                credit.getStatus()
        );
        sendEmail(email, subject, body);
    }

    @Override
    public void visit(DepositEntity deposit) {
        String email = getUserEmailFromDeposit(deposit);
        String subject = "Deposit Notification - " + deposit.getType();
        String body = String.format(
                "Your %s deposit is earning interest at %.2f%%. Current amount: %s",
                deposit.getType(),
                deposit.getInterestRate(),
                deposit.getInitialAmount()
        );
        sendEmail(email, subject, body);
    }

    private void sendEmail(String email, String subject, String body) {
        log.info("Sending email to: {} with subject: {}", email, subject);
        // TODO: Integrate with actual email service (JavaMailSender, SendGrid, etc.)
        System.out.println("EMAIL [" + email + "]: " + subject + " - " + body);
    }

    private String getUserEmailFromAccount(AccountEntity account) {
        // TODO: Fetch email from User service by userId
        return "user_" + account.getUserId() + "@bank.kz";
    }

    private String getUserEmailFromCredit(CreditEntity credit) {
        // TODO: Fetch email from User service by userId
        return "user_" + credit.getUserId() + "@bank.kz";
    }

    private String getUserEmailFromDeposit(DepositEntity deposit) {
        // TODO: Fetch email from User service by userId
        return "user_" + deposit.getUserId() + "@bank.kz";
    }
}