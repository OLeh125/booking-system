package com.learn.booking.bookingsystem.service.mongo.impl;

import com.learn.booking.bookingsystem.controller.model.users.response.AccountResponse;
import com.learn.booking.bookingsystem.db.mongo.Account;
import com.learn.booking.bookingsystem.db.repository.mongo.MongoAccountRepository;
import com.learn.booking.bookingsystem.service.mapper.AccountMapper;
import com.learn.booking.bookingsystem.service.mongo.MongoAccountService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MongoAccountServiceImpl implements MongoAccountService {

    private final AccountMapper accountMapper;
    private final MongoAccountRepository accountRepository;

    @Override
    public AccountResponse create(Account account) {
        return accountMapper.mongoAccountToAccountResponse(accountRepository.insert(account));
    }

    @Override
    public List<AccountResponse> getAll() {
        return null;
    }

    @Override
    public List<AccountResponse> createAll(List<Account> accounts) {
        return accountRepository.insert(accounts).stream().map(accountMapper::mongoAccountToAccountResponse)
            .collect(Collectors.toList());
    }

    @Override
    public AccountResponse get(UUID account) {
        return null;
    }
}
