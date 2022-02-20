package com.learn.booking.bookingsystem.db.repository;

import com.learn.booking.bookingsystem.db.model.Order;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select o from Order o JOIN fetch o.client JOIN fetch o.tickets where o.uuid = :uuid")
    @Transactional(readOnly = true)
    Optional<Order> getByUuid(UUID uuid);

    @Query("select o from Order o JOIN fetch o.client JOIN fetch o.tickets where o.uuid in (:uuids)")
    @Transactional(readOnly = true)
    List<Order> findAllByUuid(List<UUID> uuids);

    @Modifying
    @Query("delete from Order o where o.uuid = :uuid")
    void deleteByUuid(UUID uuid);
}

