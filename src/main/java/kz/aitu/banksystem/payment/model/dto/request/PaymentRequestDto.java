package kz.aitu.banksystem.payment.model.dto.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PaymentRequestDto {

    private BigDecimal amount;
    private String currency;
}