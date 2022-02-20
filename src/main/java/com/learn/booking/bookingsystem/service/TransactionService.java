package com.learn.booking.bookingsystem.service;

import java.util.UUID;

public interface TransactionService {

    void transactAllInOneAccount(UUID userUuid, UUID accountUuid);
}
