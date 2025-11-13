package kz.aitu.banksystem.credit.observer.impl;

import kz.aitu.banksystem.credit.observer.CreditEvent;
import kz.aitu.banksystem.credit.observer.CreditObserver;
import kz.aitu.banksystem.credit.model.entity.CreditEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreditNotificationObserver implements CreditObserver {

    @Override
    public void update(CreditEvent event, CreditEntity credit) {
        switch (event) {
            case CREATED:
                log.info("Credit created for user " + credit.getUserId() + ": amount " + credit.getAmount() + ", type " + credit.getCreditType());
                break;
            case PAYMENT_MADE:
                log.info("Payment made for credit " + credit.getId() + ": remaining amount " + credit.getRemainingAmount());
                break;
            case OVERDUE:
                log.warn("Credit " + credit.getId() + " is overdue for user " + credit.getUserId());
                break;
            case CLOSED:
                log.info("Credit " + credit.getId() + " closed for user " + credit.getUserId());
                break;
        }
    }
}


