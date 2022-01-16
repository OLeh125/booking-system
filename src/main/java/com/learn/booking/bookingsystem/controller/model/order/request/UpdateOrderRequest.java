package com.learn.booking.bookingsystem.controller.model.order.request;

import com.learn.booking.bookingsystem.service.annotations.Immutable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderRequest {

    @Immutable
    private Integer id;
    @Immutable
    private UUID uuid;
    @Immutable
    private LocalDateTime createdAt;
    @Immutable
    private UUID clientUuid;
    private List<UUID> ticketsUuid;

}
