package com.learn.booking.bookingsystem.db.repository;

import com.learn.booking.bookingsystem.db.model.Account;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AccountRepository extends JpaRepository<Account, Integer>, TransactionRepository {

    @Transactional(readOnly = true)
    Set<Account> getAllByOwnerId(Integer ownerId);

    @Transactional(readOnly = true)
    List<Account> getAllByOwnerUuid(UUID ownerUuid);

    @Modifying
    @Query("update Account a set a.balance = a.balance + ?1 where a.uuid = ?2")
    void transactToAccount(BigDecimal amount, UUID accountUuid);

    @Transactional(readOnly = true)
    @Query("select a from Account a join fetch a.currency where a.uuid = :uuid")
    Account getOneByUuid(UUID uuid);
}
