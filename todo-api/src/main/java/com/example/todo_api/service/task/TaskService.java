package com.example.todo_api.service.task;

public interface TaskService {

    TaskEntity find(Long taskId);

    TaskEntity create(String title);

    Iterable<TaskEntity> collect(int limit, long offset);
}
