package com.learn.booking.bookingsystem.db.mongo;

import com.learn.booking.bookingsystem.enums.TicketStatus;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("ticket")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Ticket {

    @MongoId
    private String id;
    private UUID uuid;
    private Integer number;
    private String additionalInformation;
    private TicketStatus status;
    private String seat;
    private BigDecimal price;
    private UUID eventId;

}
