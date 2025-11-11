package kz.aitu.banksystem.account.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountViewResponseDto {
    private String accountNumber;
    private Double balance;
    private String currency;
}