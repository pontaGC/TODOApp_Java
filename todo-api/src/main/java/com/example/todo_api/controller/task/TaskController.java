package com.example.todo_api.controller.task;

import com.example.todo_api.service.task.TaskEntity;
import com.example.todo_api.service.task.TaskService;
import com.example.todoapi.controller.TasksApi;
import com.example.todoapi.model.PageDTO;
import com.example.todoapi.model.TaskDTO;
import com.example.todoapi.model.TaskForm;
import com.example.todoapi.model.TaskListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController implements TasksApi {

    private final TaskService taskService;

    @Override
    public ResponseEntity<TaskDTO> createTask(TaskForm form) {
        var entity = this.taskService.create(form.getTitle());

        var dto = new TaskDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        return ResponseEntity
                .created(URI.create("/tasks/" + dto.getId()))
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

    @Override
    public ResponseEntity<TaskListDTO> getTaskList(Integer limit, Long offset) {

        var body = new TaskListDTO();

        var entities = this.taskService.collect(limit, offset);
        var taskDTOs = convertTaskEntityToDTO(entities);
        body.setResults(taskDTOs);
        body.setPage(createPageDTO(limit, offset, taskDTOs.size()));
        return ResponseEntity.ok(body);
    }

    private static List<TaskDTO> convertTaskEntityToDTO(Iterable<TaskEntity> source){
        var results = new ArrayList<TaskDTO>();
        for (var entity : source){
            var dto = convertTaskEntityToDTO(entity);
            results.add(dto);
        }

        return results;
    }

    private static TaskDTO convertTaskEntityToDTO(TaskEntity entity){
        var dto = new TaskDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        return dto;
    }

    private static PageDTO createPageDTO(int limit, long offset, int size){
        var pageDTO = new PageDTO();
        pageDTO.setLimit(limit);
        pageDTO.setOffset(offset);
        pageDTO.setSize(size);
        return pageDTO;
    }

    // コントローラ個別で例外処理したい場合、以下のようなに例外をハンドルできる
    // コントローラ個別とコントローラ共通(ControllerAdvice)両方存在する場合、コントローラ個別が優先される
//    @ExceptionHandler({MethodArgumentNotValidException.class})
//    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
//        var errors = ex.getFieldErrors();
//        return ResponseEntity.badRequest().build();
//    }
}
