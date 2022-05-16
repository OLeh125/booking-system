package com.learn.task.management.task2.repository;

import com.learn.task.management.task2.model.SubTask;
import com.learn.task.management.task2.model.Task;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
//TODO check if works fine
public class TaskRepositoryImpl implements TaskRepository {

    private final MongoTemplate mongoTemplate;
    private final MongoClient mongoClient;

    @Override
    public List<Task> getAll() {
        return mongoTemplate.findAll(Task.class);
    }

    @Override
    public List<Task> getAllByCategory(String category) {
        Query query = new Query();
        query.addCriteria(Criteria.where("category").alike(Example.of(category)));
        return mongoTemplate.find(query, Task.class);
    }

    @Override
    public List<SubTask> getAllSubTasksByCategory(String category) {
        Query query = new Query();
        query.addCriteria(Criteria.where("category").alike(Example.of(category))).fields().include("subTasks");
        return mongoTemplate.find(query, Task.class).stream().filter(t -> Objects.nonNull(t.getSubTasks()))
            .flatMap(t -> t.getSubTasks().stream()).collect(Collectors.toList());
    }

    //TODO check if works
    @Override
    public List<Task> findOverdue() {
        Query query = new Query();
        query.addCriteria(Criteria.where("deadLine").gte(LocalDateTime.now()));
        return mongoTemplate.find(query, Task.class);
    }

    @Override
    public Task insert(Task task) {
        MongoDatabase database = mongoClient.getDatabase("task-management");
        MongoCollection<Task> collection = database.getCollection("tasks", Task.class);
        collection.insertOne(task);
        return task;
    }

    @Override
    public Task update(Task task) {
        Task find = mongoTemplate.findOne(Query.query(Criteria.where("uuid").is(task.getUuid())), Task.class);
        if (find == null) {
            throw new RuntimeException("task is not found");
        }
        setAllFields(find, task);
        return mongoTemplate.save(find, "task");
    }

    private void setAllFields(Task find, Task task) {
        find.setName(task.getName());
        find.setCategory(task.getCategory());
        find.setDeadLine(task.getDeadLine());
        find.setSubTasks(task.getSubTasks());
        find.setDescription(task.getDescription());
        find.setDateOfCreation(task.getDateOfCreation());

    }

    @Override
    public void delete(UUID taskUuid) {
        mongoTemplate.remove(Query.query(Criteria.where("uuid").is(taskUuid)));
    }

    @Override
    public Task insertSub(UUID taskUuid, List<SubTask> subTasksToAdd) {
        Task find = mongoTemplate.findOne(Query.query(Criteria.where("uuid").is(taskUuid)), Task.class);
        if (find == null) {
            throw new RuntimeException("task is not found");
        }
        List<SubTask> subTasks = ListUtils.emptyIfNull(find.getSubTasks());
        subTasks.addAll(subTasksToAdd);
        find.setSubTasks(subTasks);
        return mongoTemplate.save(find, "task");
    }

    @Override
    public Task updateSub(UUID taskUuid, List<SubTask> subTasks) {
        Task find = mongoTemplate.findOne(Query.query(Criteria.where("uuid").is(taskUuid)), Task.class);
        if (find == null) {
            throw new RuntimeException("task is not found");
        }
        find.setSubTasks(subTasks);
        return mongoTemplate.save(find, "task");
    }

    @Override
    public Task deleteSub(UUID taskUuid, List<UUID> subTaskUuids) {
        Task find = mongoTemplate.findOne(Query.query(Criteria.where("uuid").is(taskUuid)), Task.class);
        if (find == null) {
            throw new RuntimeException("task is not found");
        }
        List<SubTask> subTasks = ListUtils.emptyIfNull(find.getSubTasks()).stream()
            .filter(st -> subTaskUuids.contains(st.getUuid())).collect(Collectors.toList());
        find.setSubTasks(subTasks);
        return mongoTemplate.save(find, "task");

    }

    @Override
    public List<Task> searchByDescription(String template) {
        Query query = new Query();
        query.addCriteria(Criteria.where("description").alike(Example.of(template)));
        return mongoTemplate.find(query, Task.class);
    }

    @Override
    public List<Task> searchBySubTaskName(String template) {
        Query query = new Query();
        query.addCriteria(Criteria.where("subTasks.name").alike(Example.of(template)));
        return mongoTemplate.find(query, Task.class);
    }
}
