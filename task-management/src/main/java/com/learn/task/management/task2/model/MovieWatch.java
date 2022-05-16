package com.learn.task.management.task2.model;

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
@Document("movieWatch")
public class MovieWatch {
    @MongoId
    private String id;
    private UUID uuid;
    private UUID viewerId;
    private UUID movieId;

}