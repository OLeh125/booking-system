package com.learn.booking.bookingsystem.db.repository.impl;

import com.learn.booking.bookingsystem.db.model.Ticket;
import com.learn.booking.bookingsystem.db.repository.BookingRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
public class BookingRepositoryImpl implements BookingRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public List<Ticket> bookTickets(Long number, UUID eventUuid) {
        List<Ticket> ticketsResult = ((Session) entityManager.getDelegate())
            .createNativeQuery(
                "SELECT * FROM tickets t JOIN events e ON t.event_id = e.id WHERE e.uuid = :eventUuid LIMIT :number", Ticket.class)
            .setParameter("eventUuid", eventUuid)
            .setParameter("number", number)
            .getResultList();
        List<Ticket> tickets = ((Session) entityManager.getDelegate())
            .createNativeQuery(
                "UPDATE tickets SET status = 'RESERVED' WHERE uuid IN (:uuids) RETURNING *",
                Ticket.class)
            .setParameter("uuids",
                ticketsResult.stream().map(Ticket::getUuid).collect(Collectors.toList()))
            .getResultList();
        return tickets;
    }

}
