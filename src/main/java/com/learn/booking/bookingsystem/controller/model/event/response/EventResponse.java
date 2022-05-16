package com.learn.booking.bookingsystem.controller.model.event.response;

import com.learn.booking.bookingsystem.enums.EventStatus;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventResponse {

    private String id;
    private UUID uuid;
    private String place;
    private LocalDateTime date;
    private String description;
    private EventStatus status;
    private long ticketsNumber;

}
