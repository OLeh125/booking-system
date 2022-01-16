package com.learn.booking.bookingsystem.controller.model.ticket.request;

import com.learn.booking.bookingsystem.db.model.Event;
import com.learn.booking.bookingsystem.enums.TicketStatus;
import com.learn.booking.bookingsystem.service.annotations.Immutable;
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
public class UpdateTicketRequest {

    @Immutable
    private Integer id;
    @Immutable
    private UUID uuid;
    @Immutable
    private Integer number;
    private String additionalInformation;
    private TicketStatus status;
    private String seat;
    private BigDecimal price;
    private UUID orderUuid;
    @Immutable
    private Event eventUuid;

}
