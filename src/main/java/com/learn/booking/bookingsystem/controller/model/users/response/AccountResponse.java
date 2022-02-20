package com.learn.booking.bookingsystem.controller.model.users.response;

import com.learn.booking.bookingsystem.db.model.Currency;
import com.learn.booking.bookingsystem.db.model.User;
import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountResponse {

    private Integer id;
    private UUID uuid;
    private String type;
    private CurrencyResponse currency;
    private BigDecimal balance;

}
