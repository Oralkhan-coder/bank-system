package kz.aitu.banksystem.account.service;

import kz.aitu.banksystem.account.model.dto.AccountViewResponseDto;

import java.util.List;

public interface AccountService {
    AccountViewResponseDto createAccount(String accountNumber, Double balance, String currency, Long userId);

    double convertBalance(Long accountId, String targetCurrency);

    List<AccountViewResponseDto> getAllAccountsForUser(Long userId);

    AccountViewResponseDto findById(Long id);

    List<AccountViewResponseDto> findAll();

    void deleteById(Long id);
}