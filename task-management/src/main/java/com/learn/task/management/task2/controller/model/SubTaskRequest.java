package com.learn.task.management.task2.controller.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubTaskRequest {

    private UUID uuid;
    private String name;
    private String description;

}
