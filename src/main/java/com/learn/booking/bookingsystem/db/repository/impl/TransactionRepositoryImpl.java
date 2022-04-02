package com.learn.booking.bookingsystem.db.repository.impl;

import com.learn.booking.bookingsystem.db.model.Account;
import com.learn.booking.bookingsystem.db.model.BalanceRatePair;
import com.learn.booking.bookingsystem.db.repository.TransactionRepository;
import java.util.Map;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.QueryHint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Slf4j
public class TransactionRepositoryImpl implements TransactionRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public BalanceRatePair withdrawAllByAccountUuid(UUID uuid) {
        log.info("withdrawAllByAccountUuid");
        BalanceRatePair balanceRate = (BalanceRatePair) ((Session) entityManager.getDelegate())
            .createNativeQuery(
                "SELECT balance, exchange_rate FROM accounts JOIN currencies ON currencies.id = accounts.currency_id WHERE accounts.uuid = :accountUuid")
            .setParameter("accountUuid", uuid)
            .setResultTransformer(new AliasToBeanResultTransformer(BalanceRatePair.class))
            .getSingleResult();

        Account account = ((Session) entityManager.getDelegate())
            .createNativeQuery("UPDATE accounts SET balance = 0.0 WHERE uuid = :accountUuid RETURNING *", Account.class)
            .setParameter("accountUuid", uuid)
            .getSingleResult();
        log.info("withdrawAllByAccountUuid 2");
        return balanceRate;
    }
}
