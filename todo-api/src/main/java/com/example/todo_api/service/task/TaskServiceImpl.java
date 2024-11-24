package com.example.todo_api.service.task;

import com.example.todo_api.repository.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    @Override
    public TaskEntity find() {
        return this.taskRepository.select()
                .map(record -> new TaskEntity(record.id(), record.title()))
                .orElseThrow(() -> new IllegalStateException("TODO"));
    }
}
