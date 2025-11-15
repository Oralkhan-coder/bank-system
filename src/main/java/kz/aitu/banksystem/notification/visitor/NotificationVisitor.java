package kz.aitu.banksystem.notification.visitor;

import kz.aitu.banksystem.account.model.entity.AccountEntity;
import kz.aitu.banksystem.credit.model.entity.CreditEntity;
import kz.aitu.banksystem.deposit.model.entity.DepositEntity;


public interface NotificationVisitor {

    void visit(AccountEntity account);
    void visit(CreditEntity credit);
    void visit(DepositEntity deposit);
}