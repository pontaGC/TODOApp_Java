package com.example.todo_api.repository.task;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TaskRepository {
    @Select("SELECT id, title FROM tasks WHERE id = #{taskId}")
    Optional<TaskRecord> select(Long taskId);

    @Select("SELECT id, title From tasks LIMIT #{limit} OFFSET #{offset}")
    List<TaskRecord> selectTasks(int limit, long offset);

    // MyBatisのInsert戻り値はVoid必須。
    // IDはDBの自動採番。@Options(~)自動でTaskRecord/idに値を入れてもらう
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO tasks (title) VALUES (#{title})")
    void insert(TaskRecord record);

    @Update("UPDATE tasks SET title = #{title} WHERE id = #{id}")
    void update(TaskRecord taskRecord);

    @Delete("DELETE FROM tasks WHERE id = #{taskId}")
    void delete(Long taskId);
}
