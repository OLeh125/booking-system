package com.learn.booking.bookingsystem.controller.model.event.request;

import com.learn.booking.bookingsystem.enums.EventStatus;
import com.learn.booking.bookingsystem.service.annotations.Immutable;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventRequest {

    @Immutable
    private Integer id;
    @Immutable
    private UUID uuid;
    private String place;
    private LocalDateTime date;
    private String description;
    private EventStatus status;
    private long ticketsNumber;

}
