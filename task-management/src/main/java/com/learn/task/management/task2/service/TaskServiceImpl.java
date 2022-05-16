package com.learn.task.management.task2.service;

import com.learn.task.management.task2.controller.model.SubTaskRequest;
import com.learn.task.management.task2.controller.model.SubTaskResponse;
import com.learn.task.management.task2.controller.model.TaskRequest;
import com.learn.task.management.task2.controller.model.TaskResponse;
import com.learn.task.management.task2.repository.TaskRepository;
import com.learn.task.management.task2.service.mapper.TaskMapper;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> getAll() {
        return taskRepository.getAll().stream().map(taskMapper::taskToTaskResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> getAllByCategory(String category) {
        return taskRepository.getAllByCategory(category).stream().map(taskMapper::taskToTaskResponse)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubTaskResponse> getAllSubTasksByCategory(String category) {
        return taskRepository.getAllSubTasksByCategory(category).stream().map(taskMapper::subTaskToSubTaskResponse)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> findOverdue() {
        return taskRepository.findOverdue().stream().map(taskMapper::taskToTaskResponse).collect(Collectors.toList());
    }

    @Override
    public TaskResponse insert(TaskRequest task) {
        return taskMapper.taskToTaskResponse(taskRepository.insert(taskMapper.taskRequestToTask(task)));
    }

    @Override
    public TaskResponse update(TaskRequest task) {
        return taskMapper.taskToTaskResponse(taskRepository.update(taskMapper.taskRequestToTask(task)));
    }

    @Override
    public void delete(UUID taskUuid) {
        taskRepository.delete(taskUuid);
    }

    @Override
    public TaskResponse insertSub(UUID taskUuid, List<SubTaskRequest> subTasks) {
        return taskMapper.taskToTaskResponse(taskRepository.insertSub(taskUuid,
            subTasks.stream().map(taskMapper::subTaskRequestToSubTask).collect(Collectors.toList())));
    }

    @Override
    public TaskResponse updateSub(UUID taskUuid, List<SubTaskRequest> subTasks) {
        return taskMapper.taskToTaskResponse(taskRepository.updateSub(taskUuid,
            subTasks.stream().map(taskMapper::subTaskRequestToSubTask).collect(Collectors.toList())));
    }

    @Override
    public TaskResponse deleteSub(UUID taskUuid, List<UUID> subTaskUuids) {
        return taskMapper.taskToTaskResponse(taskRepository.deleteSub(taskUuid, subTaskUuids));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> searchByDescription(String template) {
        return taskRepository.searchByDescription(template).stream().map(taskMapper::taskToTaskResponse)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> searchBySubTaskName(String template) {
        return taskRepository.searchBySubTaskName(template).stream().map(taskMapper::taskToTaskResponse)
            .collect(Collectors.toList());
    }
}
