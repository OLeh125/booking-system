package com.learn.task.management.task2.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Document("user")
public class User {

    @MongoId
    private String id;
    private UUID uuid;
    private String name;
    private String surname;
    private String email;
    private String password;
}
