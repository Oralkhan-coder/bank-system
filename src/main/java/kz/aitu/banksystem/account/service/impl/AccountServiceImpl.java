package kz.aitu.banksystem.account.service.impl;

import kz.aitu.banksystem.account.converter.AccountConverterImpl;
import kz.aitu.banksystem.account.factory.AccountFactory;
import kz.aitu.banksystem.account.model.dto.AccountViewResponseDto;
import kz.aitu.banksystem.account.model.entity.AccountEntity;
import kz.aitu.banksystem.account.repository.AccountRepository;
import kz.aitu.banksystem.account.service.AccountService;
import kz.aitu.banksystem.account.strategy.CurrencyConversionStrategy;
import kz.aitu.banksystem.core.exeption.ServiceValidationException;
import kz.aitu.banksystem.core.model.statics.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

import static kz.aitu.banksystem.account.util.MessageCodes.INVALID_PHONE_NUMBER;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountConverterImpl converter;
    private final CurrencyConversionStrategy conversionStrategy;
    private final AccountFactory accountFactory;

    @Override
    public AccountViewResponseDto createAccount(String accountNumber, Double balance, String currency, Long userId) {
        AccountEntity entity = accountFactory.createAccount(accountNumber, balance, currency, userId);
        AccountEntity savedEntity = accountRepository.save(entity);
        return converter.convert(savedEntity);
    }

    @Override
    public double convertBalance(Long accountId, String targetCurrency) {
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return conversionStrategy.convert(account.getBalance(), account.getCurrency(), targetCurrency);
    }

    @Override
    public List<AccountViewResponseDto> getAllAccountsForUser(Long userId) {
        return accountRepository.findAllActiveAccountsByUserId(userId)
                .stream()
                .map(converter::convert)
                .toList();
    }

    @Override
    public AccountViewResponseDto findById(Long id) {
        AccountEntity account = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        return converter.convert(account);
    }

    @Override
    public List<AccountViewResponseDto> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(converter::convert)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new EntityNotFoundException("Account not found");
        }
        accountRepository.deleteById(id);
    }

    @Override
    public AccountEntity findByAccountNumber(String number) {
        if (number == null || number.isEmpty()) {
            throw new ServiceValidationException(INVALID_PHONE_NUMBER, ErrorCode.INVALID_ARGUMENT);
        }
        return accountRepository.findByAccountNumber(number);
    }
}