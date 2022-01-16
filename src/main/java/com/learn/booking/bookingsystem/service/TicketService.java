package com.learn.booking.bookingsystem.service;


import com.learn.booking.bookingsystem.controller.model.ticket.request.CreateTicketRequest;
import com.learn.booking.bookingsystem.controller.model.ticket.request.UpdateTicketRequest;
import com.learn.booking.bookingsystem.controller.model.ticket.response.TicketResponse;
import java.util.List;
import java.util.UUID;

public interface TicketService {

    TicketResponse createTicket(CreateTicketRequest ticket);

    TicketResponse updateTicket(UpdateTicketRequest ticket, UUID ticketUuid);

    void deleteTicket(UUID uuid);

    List<TicketResponse> bookTickets(long number, UUID eventUuid);

    TicketResponse getTicket(UUID uuid);

    List<TicketResponse> getTickets(List<UUID> uuid);

}
