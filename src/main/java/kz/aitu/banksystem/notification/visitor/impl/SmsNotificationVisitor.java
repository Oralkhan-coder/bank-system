package kz.aitu.banksystem.notification.visitor.impl;

import kz.aitu.banksystem.account.model.entity.AccountEntity;
import kz.aitu.banksystem.credit.model.entity.CreditEntity;
import kz.aitu.banksystem.deposit.model.entity.DepositEntity;
import kz.aitu.banksystem.notification.visitor.NotificationVisitor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * SMS notification visitor.
 * Handles sending SMS notifications for different financial entities.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SmsNotificationVisitor implements NotificationVisitor {

    @Override
    public void visit(AccountEntity account) {
        String phone = getUserPhoneFromAccount(account);
        String message = String.format(
                "Bank Alert: Acc %s updated. Balance: %s %s",
                account.getAccountNumber().substring(account.getAccountNumber().length() - 4),
                account.getBalance(),
                account.getCurrency()
        );
        sendSms(phone, message);
    }

    @Override
    public void visit(CreditEntity credit) {
        String phone = getUserPhoneFromCredit(credit);
        String message = String.format(
                "Credit Alert: %s - Remaining: %s. Status: %s",
                credit.getCreditType(),
                credit.getRemainingAmount(),
                credit.getStatus()
        );
        sendSms(phone, message);
    }

    @Override
    public void visit(DepositEntity deposit) {
        String phone = getUserPhoneFromDeposit(deposit);
        String message = String.format(
                "Deposit Alert: %s earning %.2f%% interest. Amount: %s",
                deposit.getType(),
                deposit.getInterestRate(),
                deposit.getInitialAmount()
        );
        sendSms(phone, message);
    }

    private void sendSms(String phone, String message) {
        log.info("Sending SMS to: {} with message: {}", phone, message);
        // TODO: Integrate with SMS provider (Twilio, AWS SNS, local provider, etc.)
        System.out.println("SMS [" + phone + "]: " + message);
    }

    private String getUserPhoneFromAccount(AccountEntity account) {
        // TODO: Fetch phone from User service by userId
        return "+7-XXX-" + account.getUserId();
    }

    private String getUserPhoneFromCredit(CreditEntity credit) {
        // TODO: Fetch phone from User service by userId
        return "+7-XXX-" + credit.getUserId();
    }

    private String getUserPhoneFromDeposit(DepositEntity deposit) {
        // TODO: Fetch phone from User service by userId
        return "+7-XXX-" + deposit.getUserId();
    }
}