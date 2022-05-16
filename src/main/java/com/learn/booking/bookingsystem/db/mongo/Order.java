package com.learn.booking.bookingsystem.db.mongo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document("order")
public class Order {

    @MongoId
    private String id;
    private UUID uuid;
    private LocalDateTime createdAt;
    private UUID clientId;
    private List<Ticket> tickets;

}
