package com.learn.task.management.task2.service;

import com.learn.task.management.task2.controller.model.SubTaskRequest;
import com.learn.task.management.task2.controller.model.SubTaskResponse;
import com.learn.task.management.task2.controller.model.TaskRequest;
import com.learn.task.management.task2.controller.model.TaskResponse;
import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<TaskResponse> getAll();

    List<TaskResponse> getAllByCategory(String category);

    List<SubTaskResponse> getAllSubTasksByCategory(String category);

    List<TaskResponse> findOverdue();

    TaskResponse insert(TaskRequest task);

    TaskResponse update(TaskRequest task);

    void delete(UUID taskUuid);

    TaskResponse insertSub(UUID taskUuid, List<SubTaskRequest> subTasks);

    TaskResponse updateSub(UUID taskUuid, List<SubTaskRequest> subTasks);

    TaskResponse deleteSub(UUID taskUuid, List<UUID> subTaskUuids);

    List<TaskResponse> searchByDescription(String template);

    List<TaskResponse> searchBySubTaskName(String template);
}
