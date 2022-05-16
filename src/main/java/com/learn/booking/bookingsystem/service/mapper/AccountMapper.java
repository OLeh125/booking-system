package com.learn.booking.bookingsystem.service.mapper;

import com.learn.booking.bookingsystem.controller.model.users.response.AccountResponse;
import com.learn.booking.bookingsystem.db.model.Account;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountMapper {

    private final CurrencyMapper currencyMapper;

    public Set<AccountResponse> accountsToAccountsResponses(Set<Account> accounts) {
        return CollectionUtils.emptyIfNull(accounts).stream().map(a -> accountToAccountResponse(a)).collect(Collectors.toSet());
    }

    public AccountResponse accountToAccountResponse(Account account) {
        return AccountResponse.builder()
            .id(account.getId().toString())
            .uuid(account.getUuid())
            .type(account.getType())
            .currency(currencyMapper.currencyToCurrencyResponse(account.getCurrency()))
            .ownerId(account.getOwner() != null ? account.getOwner().getUuid(): null)
            .balance(account.getBalance())
            .build();
    }

    public Set<AccountResponse> mongoAccountsToAccountsResponses(Set<com.learn.booking.bookingsystem.db.mongo.Account> accounts) {
        return CollectionUtils.emptyIfNull(accounts).stream().filter(Objects::nonNull).map(a -> mongoAccountToAccountResponse(a)).collect(Collectors.toSet());
    }

    public AccountResponse mongoAccountToAccountResponse(com.learn.booking.bookingsystem.db.mongo.Account account) {
        return AccountResponse.builder()
            .id(account.getId())
            .uuid(account.getUuid())
            .type(account.getType())
            .currency(currencyMapper.mongoCurrencyToCurrencyResponse(account.getCurrency()))
            .ownerId(account.getOwnerId())
            .balance(account.getBalance())
            .build();
    }

    public com.learn.booking.bookingsystem.db.mongo.Account accountResponseToMongoAccount(AccountResponse account) {
        return com.learn.booking.bookingsystem.db.mongo.Account.builder()
            .uuid(account.getUuid())
            .type(account.getType())
            .currency(currencyMapper.currencyResponseToMongoCurrency(account.getCurrency()))
            .ownerId(account.getOwnerId())
            .balance(account.getBalance())
            .build();
    }
}
