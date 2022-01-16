package com.learn.booking.bookingsystem.db.repository;

import java.util.UUID;

public interface EventBookingRepository {

    Integer bookTickets(Integer number, UUID eventUuid);

}
