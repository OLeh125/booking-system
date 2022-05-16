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
@Document("friendship")
public class Friendship {
    @MongoId
    private String id;
    private UUID uuid;
    private UUID firstMember;
    private UUID secondMember;
    private LocalDateTime startDate;
}
