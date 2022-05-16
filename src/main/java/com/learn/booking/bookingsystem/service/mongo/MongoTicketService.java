package com.learn.booking.bookingsystem.service.mongo;

import com.learn.booking.bookingsystem.controller.model.ticket.response.TicketResponse;
import com.learn.booking.bookingsystem.controller.model.users.response.UserResponse;
import com.learn.booking.bookingsystem.db.mongo.Ticket;
import com.learn.booking.bookingsystem.db.mongo.User;
import java.util.List;
import java.util.UUID;

public interface MongoTicketService {

    TicketResponse create(Ticket ticket);

    List<TicketResponse> getAll();

    List<TicketResponse> createAll(List<Ticket> tickets);

    TicketResponse get(UUID ticketUuid);

}
