package com.learn.booking.bookingsystem.service.impl;

import com.learn.booking.bookingsystem.controller.model.ticket.request.CreateTicketRequest;
import com.learn.booking.bookingsystem.controller.model.ticket.request.UpdateTicketRequest;
import com.learn.booking.bookingsystem.controller.model.ticket.response.TicketResponse;
import com.learn.booking.bookingsystem.db.model.Ticket;
import com.learn.booking.bookingsystem.db.repository.EventRepository;
import com.learn.booking.bookingsystem.db.repository.TicketRepository;
import com.learn.booking.bookingsystem.exception.NotFoundException;
import com.learn.booking.bookingsystem.service.PatchHandler;
import com.learn.booking.bookingsystem.service.TicketService;
import com.learn.booking.bookingsystem.service.mapper.TicketMapper;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final PatchHandler patchHandler;
    private final EventRepository eventRepository;

    @Override
    public TicketResponse createTicket(CreateTicketRequest ticketRequest) {
        return ticketMapper.ticketToTicketResponse(ticketRepository
            .save(createTicketRequestToTicket(ticketRequest)));
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

    @Override
    public TicketResponse updateTicket(UpdateTicketRequest ticketRequest, UUID ticketUuid) {
        Ticket ticketApplyTo = ticketRepository.getByUuid(ticketUuid)
            .orElseThrow(() -> new NotFoundException("Ticket is not found"));
        return ticketMapper.ticketToTicketResponse(patchHandler.apply(ticketApplyTo, ticketRequest));
    }

    @Override
    public void deleteTicket(UUID uuid) {
        ticketRepository.deleteByUuid(uuid);
    }

    @Override
    public List<TicketResponse> bookTickets(long number, UUID eventUuid) {
        return ticketRepository.bookTickets(number, eventUuid).stream()
            .map(ticketMapper::ticketToTicketResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TicketResponse getTicket(UUID uuid) {
        return ticketMapper.ticketToTicketResponse(ticketRepository.getByUuid(uuid)
            .orElseThrow(() -> new NotFoundException("Ticket is not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketResponse> getTickets(List<UUID> uuids) {
        return ticketRepository.getAllByUuid(uuids).stream()
            .map(t -> ticketMapper.ticketToTicketResponse(t)).collect(Collectors.toList());
    }
}
