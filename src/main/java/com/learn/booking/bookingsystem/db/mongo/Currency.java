package com.learn.booking.bookingsystem.db.mongo;

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
public class Currency {

    private String id;
    private UUID uuid;
    private String name;
    private String shortName;
    private BigDecimal exchangeRate;
}
