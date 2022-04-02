package com.learn.booking.bookingsystem.service.impl;

import com.learn.booking.bookingsystem.db.model.Account;
import com.learn.booking.bookingsystem.db.repository.AccountRepository;
import com.learn.booking.bookingsystem.service.DemoFacade;
import com.learn.booking.bookingsystem.service.TransactionService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemoFacadeImpl implements DemoFacade {

    private final TransactionService transactionService;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public void checkIfThreadSafe(UUID userUuid) {
        List<Account> accounts = accountRepository.getAllByOwnerUuid(userUuid);
        List<CompletableFuture<Void>> completableFutures = new ArrayList<>();
        for (Account account : accounts) {
            CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(
                () -> check(userUuid, account.getUuid()));
            completableFutures.add(voidCompletableFuture);
        }
        completableFutures.forEach(CompletableFuture::join);
    }

    private synchronized void check(UUID userUuid, UUID accountUuid) {
        log.info("check");
        transactionService.transactAllInOneAccount(userUuid, accountUuid);
        log.info("check 2");

    }
}
