package kz.aitu.banksystem.deposit.factory;

import kz.aitu.banksystem.deposit.model.DepositType;
import kz.aitu.banksystem.deposit.model.entity.DepositEntity;
import org.springframework.stereotype.Component;

@Component
public class DepositFactory {

    public DepositEntity createDeposit(DepositType type, Double amount, Double rate, Long userId, Integer months) {
        return DepositEntity.builder()
                .type(type)
                .initialAmount(amount)
                .interestRate(rate)
                .userId(userId)
                .durationMonths(months)
                .build();
    }
}