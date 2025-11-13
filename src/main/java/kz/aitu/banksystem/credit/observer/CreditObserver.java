package kz.aitu.banksystem.credit.observer;

import kz.aitu.banksystem.credit.model.entity.CreditEntity;

public interface CreditObserver {
    void update(CreditEvent event, CreditEntity credit);
}

