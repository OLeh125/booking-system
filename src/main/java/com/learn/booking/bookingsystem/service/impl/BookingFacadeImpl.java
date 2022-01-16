package com.learn.booking.bookingsystem.service.impl;

import com.learn.booking.bookingsystem.controller.model.order.request.CreateOrderRequest;
import com.learn.booking.bookingsystem.controller.model.order.response.OrderResponse;
import com.learn.booking.bookingsystem.controller.model.ticket.response.TicketResponse;
import com.learn.booking.bookingsystem.controller.model.users.response.UserResponse;
import com.learn.booking.bookingsystem.service.BookingFacade;
import com.learn.booking.bookingsystem.service.EventService;
import com.learn.booking.bookingsystem.service.OrderService;
import com.learn.booking.bookingsystem.service.TicketService;
import com.learn.booking.bookingsystem.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class BookingFacadeImpl implements BookingFacade {

    private final EventService eventService;
    private final UserService userService;
    private final TicketService ticketService;
    private final OrderService orderService;

    @Override
    public OrderResponse bookToEvent(UUID eventUuid, UUID userUuid, Integer numberOfTickets) {
        UserResponse user = userService.getUser(userUuid);
        eventService.bookTickets(numberOfTickets, eventUuid);
        List<TicketResponse> tickets = ticketService.bookTickets(numberOfTickets, eventUuid);
        CreateOrderRequest createOrder = CreateOrderRequest.builder()
            .clientUuid(user.getUuid())
            .ticketsUuids(tickets.stream().map(TicketResponse::getUuid).collect(Collectors.toList()))
            .createdAt(LocalDateTime.now())
            .build();
        return orderService.createOrder(createOrder);
    }

}