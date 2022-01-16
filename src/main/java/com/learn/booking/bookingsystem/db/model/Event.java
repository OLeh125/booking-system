package com.learn.booking.bookingsystem.db.model;

import com.learn.booking.bookingsystem.enums.EventStatus;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "events")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "event_id_seq", allocationSize = 1)
    private Integer id;
    private UUID uuid;
    private String place;
    private LocalDateTime date;
    private String description;
    @Enumerated(EnumType.STRING)
    private EventStatus status;
    private long ticketsNumber;

}
