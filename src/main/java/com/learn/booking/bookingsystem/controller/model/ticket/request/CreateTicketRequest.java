package com.learn.booking.bookingsystem.controller.model.ticket.request;

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
public class CreateTicketRequest {

    private Long id;
    private UUID uuid;
    private Integer number;
    private String additionalInformation;
    private TicketStatus status;
    private String seat;
    private BigDecimal price;
    private UUID eventUuid;

}
