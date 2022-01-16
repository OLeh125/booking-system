package com.learn.booking.bookingsystem.service;

import com.learn.booking.bookingsystem.controller.model.order.response.OrderResponse;
import java.util.UUID;

public interface BookingFacade {

    OrderResponse bookToEvent(UUID eventUuid, UUID userUuid, Integer numberOfTickets);

}
