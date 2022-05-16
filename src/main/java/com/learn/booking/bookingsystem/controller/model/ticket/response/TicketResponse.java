package com.learn.booking.bookingsystem.controller.model.ticket.response;

import com.learn.booking.bookingsystem.controller.model.event.response.EventResponse;
import com.learn.booking.bookingsystem.enums.TicketStatus;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TicketResponse {

    private String id;
    private UUID uuid;
    private Integer number;
    private String additionalInformation;
    private TicketStatus status;
    private String seat;
    private BigDecimal price;
    private EventResponse event;

}
