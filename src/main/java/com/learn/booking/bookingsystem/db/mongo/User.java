package com.learn.booking.bookingsystem.db.mongo;

import com.learn.booking.bookingsystem.enums.UserStatus;
import java.util.Set;
import java.util.UUID;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {

    @MongoId
    private String id;
    private UUID uuid;
    private String name;
    private String surname;
    private String email;
    private String password;
    private UserStatus status = UserStatus.ACTIVE;
}
