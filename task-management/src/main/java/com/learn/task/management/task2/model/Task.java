package com.learn.task.management.task2.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Task {

    @MongoId
    private String id;
    private UUID uuid;
    private LocalDateTime dateOfCreation;
    private LocalDateTime deadLine;
    private String name;
    private String description;
    private List<SubTask> subTasks;
    private String category;

}



