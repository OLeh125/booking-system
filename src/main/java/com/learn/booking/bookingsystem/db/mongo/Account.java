package com.learn.booking.bookingsystem.db.mongo;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document("account")
public class Account {
    @MongoId
    private String id;
    private UUID uuid;
    private String type;
    private Currency currency;
    private BigDecimal balance;
    private UUID ownerId;
}
