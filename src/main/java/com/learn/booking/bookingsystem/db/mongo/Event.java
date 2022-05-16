package com.learn.booking.bookingsystem.db.mongo;

import com.learn.booking.bookingsystem.enums.EventStatus;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("event")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Event {
    @MongoId
    private String id;
    private UUID uuid;
    private String place;
    private LocalDateTime date;
    private String description;
    private EventStatus status;
    private long ticketsNumber;

}
