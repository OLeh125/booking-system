package com.learn.task.management.task2.controller.model;

import com.learn.task.management.task2.model.SubTask;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskResponse {

    private UUID uuid;
    private LocalDateTime dateOfCreation;
    private LocalDateTime deadLine;
    private String name;
    private String description;
    private List<SubTask> subTasks;
    private String category;

}
