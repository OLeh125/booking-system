package com.learn.booking.bookingsystem.db.repository;

import com.learn.booking.bookingsystem.db.model.Ticket;
import java.util.List;
import java.util.UUID;

public interface BookingRepository {

    List<Ticket> bookTickets(Long number, UUID eventUuid);
}
