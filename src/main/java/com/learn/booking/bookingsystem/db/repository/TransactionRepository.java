package com.learn.booking.bookingsystem.db.repository;

import com.learn.booking.bookingsystem.db.model.BalanceRatePair;
import java.util.UUID;

public interface TransactionRepository {

    BalanceRatePair withdrawAllByAccountUuid(UUID uuid);
}
