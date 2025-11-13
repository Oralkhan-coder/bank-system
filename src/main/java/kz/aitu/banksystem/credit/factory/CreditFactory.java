package kz.aitu.banksystem.credit.factory;

import kz.aitu.banksystem.credit.model.CreditStatus;
import kz.aitu.banksystem.credit.model.CreditType;
import kz.aitu.banksystem.credit.model.entity.CreditEntity;
import org.springframework.stereotype.Component;

@Component
public class CreditFactory {
    public CreditEntity createCredit(CreditType type, Double amount, Double interestRate, Integer termMonths, Long userId) {
        return CreditEntity.builder()
                .creditType(type)
                .amount(amount)
                .interestRate(interestRate)
                .termMonths(termMonths)
                .remainingAmount(amount)
                .status(CreditStatus.ACTIVE)
                .userId(userId)
                .build();
    }
}
