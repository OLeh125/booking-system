package com.learn.booking.bookingsystem.service.mapper;

import com.learn.booking.bookingsystem.controller.model.order.response.OrderResponse;
import com.learn.booking.bookingsystem.db.model.Order;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderMapper {

    private final UserMapper userMapper;
    private final TicketMapper ticketMapper;


    public OrderResponse orderToOrderResponse(Order order){
        return OrderResponse.builder()
            .id(order.getId())
            .uuid(order.getUuid())
            .createdAt(order.getCreatedAt())
            .client(userMapper.userToUserResponse(order.getClient()))
            .tickets(order.getTickets().stream().map(ticketMapper::ticketToTicketResponse).collect(Collectors.toList()))
            .build();
    }

}
