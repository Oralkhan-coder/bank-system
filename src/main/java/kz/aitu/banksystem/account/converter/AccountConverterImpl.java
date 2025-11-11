package kz.aitu.banksystem.account.converter;

import kz.aitu.banksystem.account.model.dto.AccountViewResponseDto;
import kz.aitu.banksystem.account.model.entity.AccountEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AccountConverterImpl implements Converter<AccountEntity, AccountViewResponseDto> {

    @Override
    public AccountViewResponseDto convert(AccountEntity source) {
        return AccountViewResponseDto.builder()
                .accountNumber(source.getAccountNumber())
                .balance(source.getBalance())
                .currency(source.getCurrency())
                .build();
    }
}