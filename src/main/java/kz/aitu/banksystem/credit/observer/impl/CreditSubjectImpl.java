package kz.aitu.banksystem.credit.observer.impl;

import kz.aitu.banksystem.credit.observer.CreditEvent;
import kz.aitu.banksystem.credit.observer.CreditObserver;
import kz.aitu.banksystem.credit.observer.CreditSubject;
import kz.aitu.banksystem.credit.model.entity.CreditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreditSubjectImpl implements CreditSubject {
    private List<CreditObserver> observers = new ArrayList<>();
    @Autowired
    private CreditNotificationObserver notificationObserver;
    @Override
    public void attach(CreditObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(CreditObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(CreditEvent event, CreditEntity credit) {
        if (notificationObserver != null) {
            notificationObserver.update(event, credit);
        }
        for (CreditObserver observer : observers) {
            observer.update(event, credit);
        }
    }
}

