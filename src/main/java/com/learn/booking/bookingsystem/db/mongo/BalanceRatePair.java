package com.learn.booking.bookingsystem.db.mongo;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BalanceRatePair {

    private BigDecimal balance;
    private BigDecimal exchange_rate;

}
