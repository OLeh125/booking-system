package com.learn.booking.bookingsystem.controller.model.order.request;

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
public class CreateOrderRequest {

    private Integer id;
    private UUID uuid;
    private LocalDateTime createdAt;
    private UUID clientUuid;
    private List<UUID> ticketsUuids;
}
