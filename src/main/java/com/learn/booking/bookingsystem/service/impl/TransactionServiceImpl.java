package com.learn.booking.bookingsystem.service.impl;

import com.learn.booking.bookingsystem.db.model.Account;
import com.learn.booking.bookingsystem.db.model.BalanceRatePair;
import com.learn.booking.bookingsystem.db.repository.AccountRepository;
import com.learn.booking.bookingsystem.service.TransactionService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;

    @Override
    //TODO is jpa repository thread safe?
    //TODO or this code is thread safe cause Transaction (transaction level READ COMMITTED by default prevent dirty read but doesn't non repeatable read)
    //TODO or if it's not thread safe. Ask regarding locks and tr levels.
    public void transactAllInOneAccount(UUID userUuid, UUID accountUuid) {
        List<Account> accounts = accountRepository.getAllByOwnerUuid(userUuid)
            .stream().filter(a -> !a.getUuid().equals(accountUuid)).collect(Collectors.toList());
        BigDecimal exchangeRate = accountRepository.getOneByUuid(accountUuid).getCurrency().getExchangeRate();
        List<CompletableFuture<Void>> transactions  = new ArrayList<>();
        for (Account account : accounts) {
            CompletableFuture<Void> transaction = CompletableFuture
                .supplyAsync(() -> accountRepository.withdrawAllByAccountUuid(account.getUuid()))
                .thenApply(p -> exchange(p))
                .thenAccept(b -> transactToAccount(accountUuid, exchangeRate, b));
            transactions.add(transaction);
        }
        transactions.forEach(CompletableFuture::join);
    }

    private void transactToAccount(UUID accountUuid, BigDecimal exchangeRate, BigDecimal amount) {
        accountRepository.transactToAccount(amount.divide(exchangeRate, RoundingMode.DOWN), accountUuid);
    }

    private BigDecimal exchange(BalanceRatePair amountRate) {
        return amountRate.getBalance().multiply(amountRate.getExchange_rate());
    }
}
