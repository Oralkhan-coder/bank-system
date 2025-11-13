package kz.aitu.banksystem.credit.observer;

import kz.aitu.banksystem.credit.model.entity.CreditEntity;

public interface CreditSubject {
    void attach(CreditObserver observer);
    void detach(CreditObserver observer);
    void notifyObservers(CreditEvent event, CreditEntity credit);
}

