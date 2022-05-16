package com.learn.booking.bookingsystem.controller.model.order.response;

import com.learn.booking.bookingsystem.controller.model.ticket.response.TicketResponse;
import com.learn.booking.bookingsystem.controller.model.users.response.UserResponse;
import com.learn.booking.bookingsystem.db.model.Ticket;
import com.learn.booking.bookingsystem.db.model.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderResponse {

    private String id;
    private UUID uuid;
    private LocalDateTime createdAt;
    private UserResponse client;
    private List<TicketResponse> tickets;

}
