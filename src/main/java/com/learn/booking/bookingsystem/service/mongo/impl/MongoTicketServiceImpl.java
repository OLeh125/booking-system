package com.learn.booking.bookingsystem.service.mongo.impl;

import com.learn.booking.bookingsystem.controller.model.ticket.response.TicketResponse;
import com.learn.booking.bookingsystem.db.mongo.Ticket;
import com.learn.booking.bookingsystem.db.repository.mongo.MongoTicketRepository;
import com.learn.booking.bookingsystem.service.mapper.TicketMapper;
import com.learn.booking.bookingsystem.service.mongo.MongoTicketService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class MongoTicketServiceImpl implements MongoTicketService {

    private final MongoTicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Override
    public TicketResponse create(Ticket ticket) {
        return ticketMapper.mongoTicketToTicketResponse(ticketRepository.insert(ticket));
    }

    @Override
    public List<TicketResponse> getAll() {
        return ticketRepository.findAll().stream().map(ticketMapper::mongoTicketToTicketResponse).collect(Collectors.toList());
    }

    @Override
    public List<TicketResponse> createAll(List<Ticket> tickets) {
        return ticketRepository.insert(tickets).stream().map(ticketMapper::mongoTicketToTicketResponse).collect(Collectors.toList());
    }

    @Override
    public TicketResponse get(UUID ticketUuid) {
        return null;
    }
}
