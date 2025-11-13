package kz.aitu.banksystem.credit.model.dto;

import kz.aitu.banksystem.credit.model.CreditStatus;
import kz.aitu.banksystem.credit.model.CreditType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreditViewResponseDto {
    private Long id;
    private CreditType creditType;
    private Double amount;
    private Double interestRate;
    private Integer termMonths;
    private Double remainingAmount;
    private CreditStatus status;
    private Long userId;
}

