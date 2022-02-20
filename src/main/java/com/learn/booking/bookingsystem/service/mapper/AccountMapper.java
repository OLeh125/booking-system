package com.learn.booking.bookingsystem.service.mapper;

import com.learn.booking.bookingsystem.controller.model.users.response.AccountResponse;
import com.learn.booking.bookingsystem.db.model.Account;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountMapper {

    private final CurrencyMapper currencyMapper;

    public Set<AccountResponse> accountsToAccountsResponses(Set<Account> accounts) {
        return accounts.stream().map(a -> accountToAccountResponse(a)).collect(Collectors.toSet());
    }

    private AccountResponse accountToAccountResponse(Account account) {
        return AccountResponse.builder()
            .id(account.getId())
            .uuid(account.getUuid())
            .type(account.getType())
            .currency(currencyMapper.currencyToCurrencyResponse(account.getCurrency()))
            .balance(account.getBalance())
            .build();
    }
}
