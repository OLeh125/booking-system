package com.learn.booking.bookingsystem.db.repository;

import com.learn.booking.bookingsystem.db.model.Ticket;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Integer>, BookingRepository{

    @Query("select t from Ticket t where t.uuid in (:uuids)")
    @Transactional(readOnly = true)
    List<Ticket> getAllByUuid(List<UUID> uuids);

    @Query("select t from Ticket t join fetch t.event e where t.uuid = :uuid")
    @Transactional(readOnly = true)
    Optional<Ticket> getByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);

}
