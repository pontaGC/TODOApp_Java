package com.example.todo_api.service.task;

import com.example.todo_api.repository.task.TaskRecord;
import com.example.todo_api.repository.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    @Override
    public TaskEntity find(Long taskId) {
        return this.taskRepository.select(taskId)
                .map(TaskServiceImpl::convertTaskRecord)
                .orElseThrow(() -> new TaskEntityNotFoundException(taskId));
    }

    @Override
    public TaskEntity create(String title){
        // idは自動採番のため、一旦nullを入れる (Longは参照型のため、null許容）
        var record = new TaskRecord(null, title);
        this.taskRepository.insert(record);
        return new TaskEntity(record.getId(), record.getTitle());
    }

    @Override
    public Iterable<TaskEntity> collect(int limit, long offset) {
        var allTaskRecords = this.taskRepository.selectTasks(limit, offset);
        return allTaskRecords
                .stream()
                .map(TaskServiceImpl::convertTaskRecord)
                .collect(Collectors.toList());
    }

    @Override
    public TaskEntity update(Long taskId, String title) {
        // Guard against unnecessary update
        this.throwIfTaskEntityNotFound(taskId);

        this.taskRepository.update(new TaskRecord(taskId, title));
        return this.find(taskId);
    }

    @Override
    public void delete(Long taskId) {
        this.throwIfTaskEntityNotFound(taskId);
        this.taskRepository.delete(taskId);
    }

    private static TaskEntity convertTaskRecord(TaskRecord source){
        return new TaskEntity(
                source.getId(),
                source.getTitle());
    }

    private void throwIfTaskEntityNotFound(Long taskId){
        this.taskRepository.select(taskId).orElseThrow(() -> new TaskEntityNotFoundException(taskId));
    }
}
