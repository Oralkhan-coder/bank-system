package kz.aitu.banksystem.credit.converter;

import kz.aitu.banksystem.credit.model.dto.CreditViewResponseDto;
import kz.aitu.banksystem.credit.model.entity.CreditEntity;
import org.springframework.stereotype.Component;

@Component
public class CreditConverter {
    public CreditViewResponseDto convert(CreditEntity entity) {
        CreditViewResponseDto dto = CreditViewResponseDto.builder()
                .id(entity.getId())
                .creditType(entity.getCreditType())
                .amount(entity.getAmount())
                .interestRate(entity.getInterestRate())
                .termMonths(entity.getTermMonths())
                .remainingAmount(entity.getRemainingAmount())
                .status(entity.getStatus())
                .userId(entity.getUserId())
                .build();
        return dto;
    }
}
