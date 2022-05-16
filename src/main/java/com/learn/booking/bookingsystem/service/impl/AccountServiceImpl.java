package com.learn.booking.bookingsystem.service.impl;

import com.learn.booking.bookingsystem.controller.model.users.response.AccountResponse;
import com.learn.booking.bookingsystem.db.repository.AccountRepository;
import com.learn.booking.bookingsystem.service.AccountService;
import com.learn.booking.bookingsystem.service.mapper.AccountMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public List<AccountResponse> getAll() {
        return accountRepository.findAll().stream().map(accountMapper::accountToAccountResponse)
            .collect(Collectors.toList());
    }
}
