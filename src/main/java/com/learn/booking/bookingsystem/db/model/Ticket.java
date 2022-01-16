package com.learn.booking.bookingsystem.db.model;

import com.learn.booking.bookingsystem.enums.TicketStatus;
import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tickets")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "ticket_id_seq", allocationSize = 1)
    private Integer id;
    private UUID uuid;
    private Integer number;
    @Column(name = "additional_inf")
    private String additionalInformation;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    private String seat;
    private BigDecimal price;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "event_id")
    private Event event;

}
