package com.learn.booking.bookingsystem.db.model;

import com.learn.booking.bookingsystem.enums.UserStatus;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "user_id_seq", allocationSize = 1)
    private Integer id;
    private UUID uuid;
    private String name;
    private String surname;
    private String email;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<Order> orders;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<Account> accounts;
}
