package com.learn.booking.bookingsystem.controller.model.users.response;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CurrencyResponse {

    private String id;
    private UUID uuid;
    private String name;
    private String shortName;
    private BigDecimal exchangeRate;
}
