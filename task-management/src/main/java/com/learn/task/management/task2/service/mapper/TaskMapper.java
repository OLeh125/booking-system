package com.learn.task.management.task2.service.mapper;

import com.learn.task.management.task2.controller.model.SubTaskRequest;
import com.learn.task.management.task2.controller.model.SubTaskResponse;
import com.learn.task.management.task2.controller.model.TaskRequest;
import com.learn.task.management.task2.controller.model.TaskResponse;
import com.learn.task.management.task2.model.SubTask;
import com.learn.task.management.task2.model.Task;
import org.springframework.stereotype.Service;

@Service
public class TaskMapper {

    public TaskResponse taskToTaskResponse(Task task) {
        return TaskResponse.builder()
            .uuid(task.getUuid())
            .name(task.getName())
            .description(task.getDescription())
            .category(task.getCategory())
            .deadLine(task.getDeadLine())
            .dateOfCreation(task.getDateOfCreation())
            .subTasks(task.getSubTasks())
            .build();
    }

    public SubTaskResponse subTaskToSubTaskResponse(SubTask subTask) {
        return SubTaskResponse.builder()
            .uuid(subTask.getUuid())
            .name(subTask.getName())
            .description(subTask.getDescription())
            .build();
    }

    public Task taskRequestToTask(TaskRequest task) {
        return Task.builder()
            .uuid(task.getUuid())
            .name(task.getName())
            .description(task.getDescription())
            .category(task.getCategory())
            .deadLine(task.getDeadLine())
            .dateOfCreation(task.getDateOfCreation())
            .subTasks(task.getSubTasks())
            .build();
    }

    public SubTask subTaskRequestToSubTask(SubTaskRequest subTask) {
        return SubTask.builder()
            .uuid(subTask.getUuid())
            .name(subTask.getName())
            .description(subTask.getDescription())
            .build();
    }
}
