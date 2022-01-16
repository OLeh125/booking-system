package com.learn.booking.bookingsystem.service.mapper;

import com.learn.booking.bookingsystem.controller.model.ticket.request.CreateTicketRequest;
import com.learn.booking.bookingsystem.controller.model.ticket.response.TicketResponse;
import com.learn.booking.bookingsystem.db.model.Ticket;
import com.learn.booking.bookingsystem.db.repository.EventRepository;
import com.learn.booking.bookingsystem.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketMapper {

    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    public TicketResponse ticketToTicketResponse(Ticket ticket){
        return TicketResponse.builder()
            .id(ticket.getId())
            .uuid(ticket.getUuid())
            .number(ticket.getNumber())
            .additionalInformation(ticket.getAdditionalInformation())
            .status(ticket.getStatus())
            .seat(ticket.getSeat())
            .price(ticket.getPrice())
            .event(eventMapper.eventToEventResponse(ticket.getEvent()))
            .build();
    }

    public Ticket createTicketRequestToTicket(CreateTicketRequest ticketRequest){
        return Ticket.builder()
            .id(ticketRequest.getId())
            .uuid(ticketRequest.getUuid())
            .number(ticketRequest.getNumber())
            .additionalInformation(ticketRequest.getAdditionalInformation())
            .status(ticketRequest.getStatus())
            .seat(ticketRequest.getSeat())
            .price(ticketRequest.getPrice())
            .event(eventRepository.getByUuid(ticketRequest.getUuid())
                .orElseThrow(() -> new NotFoundException("Event is not found")))
            .build();
    }

}
