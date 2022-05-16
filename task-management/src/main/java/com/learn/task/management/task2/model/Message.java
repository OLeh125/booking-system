package com.learn.task.management.task2.model;

import java.time.LocalDateTime;
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
@Document("message")
public class Message {
    @MongoId
    private String id;
    private UUID uuid;
    private UUID sender;
    private UUID receiver;
    private String content;
    private LocalDateTime createdDate;

}
