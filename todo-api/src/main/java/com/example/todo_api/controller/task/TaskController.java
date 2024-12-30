package com.example.todo_api.controller.task;

import com.example.todo_api.service.task.TaskService;
import com.example.todoapi.controller.TasksApi;
import com.example.todoapi.model.TaskDTO;
import com.example.todoapi.model.TaskForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaskController implements TasksApi {

    private final TaskService taskService;

    @Override
    public ResponseEntity<TaskDTO> createTask(TaskForm form) {
        var dto = new TaskDTO();
        dto.setId(99L);
        dto.setTitle(form.getTitle());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dto);
    }

    @Override
    public ResponseEntity<TaskDTO> showTask(Long taskId) {
        var entity = this.taskService.find(taskId);

        var body = new TaskDTO();
        body.setId(entity.getId());
        body.setTitle(entity.getTitle());
        return ResponseEntity.ok(body);
    }
}
