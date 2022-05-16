package com.learn.booking.bookingsystem.service.mapper;

import com.learn.booking.bookingsystem.controller.model.order.response.OrderResponse;
import com.learn.booking.bookingsystem.controller.model.users.response.UserResponse;
import com.learn.booking.bookingsystem.db.model.Order;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderMapper {

    private final UserMapper userMapper;
    private final TicketMapper ticketMapper;

    public OrderResponse orderToOrderResponse(Order order) {
        return OrderResponse.builder()
            .id(order.getId().toString())
            .uuid(order.getUuid())
            .createdAt(order.getCreatedAt())
            .client(userMapper.userToUserResponse(order.getClient()))
            .tickets(order.getTickets().stream().map(ticketMapper::ticketToTicketResponse).collect(Collectors.toList()))
            .build();
    }

    public com.learn.booking.bookingsystem.db.mongo.Order orderResponseToMongoOrder(OrderResponse order) {
        return com.learn.booking.bookingsystem.db.mongo.Order.builder()
            .uuid(order.getUuid())
            .createdAt(order.getCreatedAt())
            .clientId(order.getClient() != null ? order.getClient().getUuid() : null)
            .tickets(order.getTickets().stream().map(ticketMapper::ticketResponseToMongoTicket).collect(Collectors.toList()))
            .build();
    }

    //TODO use another model
    public OrderResponse mongoOrderToOrderResponse(com.learn.booking.bookingsystem.db.mongo.Order order) {
        return OrderResponse.builder()
            .id(order.getId())
            .uuid(order.getUuid())
            .createdAt(order.getCreatedAt())
            //TODO fix
            .client(UserResponse.builder().uuid(order.getClientId()).build())
            .tickets(
                order.getTickets().stream().map(ticketMapper::mongoTicketToTicketResponse).collect(Collectors.toList()))
            .build();
    }
}
