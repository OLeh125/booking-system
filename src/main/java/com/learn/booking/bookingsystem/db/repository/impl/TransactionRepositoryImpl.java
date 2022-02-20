package com.learn.booking.bookingsystem.db.repository.impl;

import com.learn.booking.bookingsystem.db.model.Account;
import com.learn.booking.bookingsystem.db.model.BalanceRatePair;
import com.learn.booking.bookingsystem.db.repository.TransactionRepository;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
public class TransactionRepositoryImpl implements TransactionRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public BalanceRatePair withdrawAllByAccountUuid(UUID uuid) {
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
        return balanceRate;
    }
}
