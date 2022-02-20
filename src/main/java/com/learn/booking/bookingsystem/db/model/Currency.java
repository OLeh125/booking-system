package com.learn.booking.bookingsystem.db.model;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "currencies")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "currency_id_seq", allocationSize = 1)
    private Integer id;
    private UUID uuid;
    private String name;
    private String shortName;
    private BigDecimal exchangeRate;
}
