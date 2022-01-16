package com.learn.booking.bookingsystem.service.mapper;

import com.learn.booking.bookingsystem.controller.model.order.request.CreateOrderRequest;
import com.learn.booking.bookingsystem.controller.model.order.response.OrderResponse;
import com.learn.booking.bookingsystem.db.model.Order;
import com.learn.booking.bookingsystem.db.repository.TicketRepository;
import com.learn.booking.bookingsystem.db.repository.UserRepository;
import com.learn.booking.bookingsystem.exception.NotFoundException;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderMapper {

    private final UserMapper userMapper;
    private final TicketMapper ticketMapper;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    public OrderResponse orderToOrderResponse(Order order){
        return OrderResponse.builder()
            .id(order.getId())
            .uuid(order.getUuid())
            .createdAt(order.getCreatedAt())
            .client(userMapper.userToUserResponse(order.getClient()))
            .tickets(order.getTickets().stream().map(ticketMapper::ticketToTicketResponse).collect(Collectors.toList()))
            .build();
    }

    public Order createOrderRequestToOrder(CreateOrderRequest orderRequest){
        return Order.builder()
            .uuid(UUID.randomUUID())
            .createdAt(LocalDateTime.now())
            .client(userRepository.getByUuid(orderRequest.getClientUuid())
                .orElseThrow(() -> new NotFoundException("User is not found")))
            .tickets(ticketRepository.getAllByUuid(orderRequest.getTicketsUuids()))
            .build();
    }

}
