package com.example.todo_api.service.task;

import com.example.todo_api.repository.task.TaskRecord;
import com.example.todo_api.repository.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    @Override
    public TaskEntity find(Long taskId) {
        return this.taskRepository.select(taskId)
                .map(record -> new TaskEntity(record.getId(), record.getTitle()))
                .orElseThrow(() -> new TaskEntityNotFoundException(taskId));
    }

    @Override
    public TaskEntity create(String title){
        // idは自動採番のため、一旦nullを入れる (Longは参照型のため、null許容）
        var record = new TaskRecord(null, title);
        this.taskRepository.insert(record);
        return new TaskEntity(record.getId(), record.getTitle());
    }
}
