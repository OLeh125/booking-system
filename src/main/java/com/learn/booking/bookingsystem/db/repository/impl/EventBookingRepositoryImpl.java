package com.learn.booking.bookingsystem.db.repository.impl;

import com.learn.booking.bookingsystem.db.repository.EventBookingRepository;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
public class EventBookingRepositoryImpl implements EventBookingRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Integer bookTickets(Integer number, UUID eventUuid) {
        return (Integer) ((Session) entityManager.getDelegate())
             .createNativeQuery(
                 "UPDATE events SET tickets_number = tickets_number - :number WHERE uuid = :eventUuid RETURNING tickets_number")
             .setParameter("number", number)
             .setParameter("eventUuid", eventUuid)
             .getSingleResult();
    }
}
