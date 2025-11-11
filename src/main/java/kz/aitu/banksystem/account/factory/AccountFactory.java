package kz.aitu.banksystem.account.factory;

import kz.aitu.banksystem.account.model.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountFactory {

    public AccountEntity createAccount(String accountNumber, Double balance, String currency, Long userId) {
        return AccountEntity.builder()
                .accountNumber(accountNumber)
                .balance(balance)
                .currency(currency)
                .userId(userId)
                .build();
    }
}