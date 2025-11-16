package kz.aitu.banksystem.payment.model.dto.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PaymentRequestDto {
    private String accountNumber;
    private Double amount;
}