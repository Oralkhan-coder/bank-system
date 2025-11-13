package kz.aitu.banksystem.credit.service;

import kz.aitu.banksystem.credit.model.CreditType;
import kz.aitu.banksystem.credit.model.dto.CreditViewResponseDto;

import java.util.List;

public interface CreditService {
    CreditViewResponseDto createCredit(CreditType type, Double amount, Double interestRate, Integer termMonths, Long userId);
    CreditViewResponseDto makePayment(Long creditId, Double paymentAmount);
    CreditViewResponseDto findById(Long id);
    List<CreditViewResponseDto> findAllByUserId(Long userId);
    List<CreditViewResponseDto> findAll();
    void checkOverdueCredits();
}
