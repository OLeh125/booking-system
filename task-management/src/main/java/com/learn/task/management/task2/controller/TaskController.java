package com.learn.task.management.task2.controller;

import com.learn.task.management.task2.controller.model.SubTaskRequest;
import com.learn.task.management.task2.controller.model.SubTaskResponse;
import com.learn.task.management.task2.controller.model.TaskRequest;
import com.learn.task.management.task2.controller.model.TaskResponse;
import com.learn.task.management.task2.service.TaskService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping()
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<TaskResponse>> getAllByCategory(@RequestParam String category) {
        return new ResponseEntity<>(taskService.getAllByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/sub/category")
    public ResponseEntity<List<SubTaskResponse>> getAllSubTasksByCategory(@RequestParam String category) {
        return new ResponseEntity<>(taskService.getAllSubTasksByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<TaskResponse>> findOverdue() {
        return new ResponseEntity<>(taskService.findOverdue(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> insert(TaskRequest task) {
        return new ResponseEntity<>(taskService.insert(task), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TaskResponse> update(TaskRequest task) {
        return new ResponseEntity<>(taskService.update(task), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam UUID taskUuid) {
        taskService.delete(taskUuid);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PostMapping("/sub")
    public ResponseEntity<TaskResponse> insertSub(@RequestParam UUID taskUuid, @RequestParam List<SubTaskRequest> subTasks) {
        return new ResponseEntity<>(taskService.insertSub(taskUuid, subTasks), HttpStatus.CREATED);
    }

    @PutMapping("/sub")
    public ResponseEntity<TaskResponse> updateSub(@RequestParam UUID taskUuid, @RequestParam List<SubTaskRequest> subTasks) {
        return new ResponseEntity<>(taskService.updateSub(taskUuid, subTasks), HttpStatus.CREATED);
    }

    @DeleteMapping("/sub")
    public ResponseEntity<TaskResponse> deleteSub(@RequestParam UUID taskUuid, @RequestParam List<UUID> subTaskUuids) {
        return new ResponseEntity<>(taskService.deleteSub(taskUuid, subTaskUuids), HttpStatus.CREATED);
    }

}
