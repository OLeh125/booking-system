package com.learn.booking.bookingsystem.db.repository;

import com.learn.booking.bookingsystem.db.model.Event;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EventRepository extends JpaRepository<Event, Integer>, EventBookingRepository {

    @Transactional(readOnly = true)
    Optional<Event> getByUuid(UUID uuid);

    @Query("select e from Event e where e.uuid in (:uuids)")
    @Transactional(readOnly = true)
    List<Event> findAllByUuid(List<UUID> uuids);

    @Modifying
    @Query("delete from Event e where e.uuid = :uuid")
    void deleteByUuid(UUID uuid);

}
