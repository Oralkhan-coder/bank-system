package kz.aitu.banksystem.payment.service.impl;

import kz.aitu.banksystem.account.model.entity.AccountEntity;
import kz.aitu.banksystem.account.service.AccountService;
import kz.aitu.banksystem.notification.visitable.impl.PushNotification;
import kz.aitu.banksystem.notification.visitable.impl.TelegramNotification;
import kz.aitu.banksystem.notification.visitor.NotificationVisitor;
import kz.aitu.banksystem.notification.visitor.impl.NotificationVisitorImpl;
import kz.aitu.banksystem.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final AccountService accountService;

    @Override
    @Transactional
    public void pay(Double amount, String accountNumber) {
        log.info("Bank Payment {}", amount);
        AccountEntity account = accountService.findByAccountNumber(accountNumber);
        account.setBalance(account.getBalance() - amount);
        NotificationVisitor visitor = new NotificationVisitorImpl();
        PushNotification pushNotification = new PushNotification(accountNumber, "Payment succeed");
        TelegramNotification telegramNotification = new TelegramNotification(accountNumber, "Payment succeed");
        pushNotification.accept(visitor);
        telegramNotification.accept(visitor);
    }
}
