package com.learn.booking.bookingsystem.service.mapper;

import com.learn.booking.bookingsystem.controller.model.ticket.response.TicketResponse;
import com.learn.booking.bookingsystem.db.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketMapper {

    private final EventMapper eventMapper;

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

}
