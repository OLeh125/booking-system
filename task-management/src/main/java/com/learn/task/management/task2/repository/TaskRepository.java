package com.learn.task.management.task2.repository;

import com.learn.task.management.task2.model.SubTask;
import com.learn.task.management.task2.model.Task;
import java.util.List;
import java.util.UUID;

public interface TaskRepository {

    List<Task> getAll();

    List<Task> getAllByCategory(String category);

    List<SubTask> getAllSubTasksByCategory(String category);

    List<Task> findOverdue();

    Task insert(Task task);

    Task update(Task task);

    void delete(UUID taskUuid);

    Task insertSub(UUID taskUuid, List<SubTask> subTasks);

    Task updateSub(UUID taskUuid, List<SubTask> subTasks);

    Task deleteSub(UUID taskUuid, List<UUID> subTaskUuids);

    List<Task> searchByDescription(String template);

    List<Task> searchBySubTaskName(String template);

}