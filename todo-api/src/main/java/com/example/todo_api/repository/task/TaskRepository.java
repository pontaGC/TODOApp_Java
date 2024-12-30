package com.example.todo_api.repository.task;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface TaskRepository {
    @Select("SELECT id, title FROM tasks WHERE id = #{taskId}")
    Optional<TaskRecord> select(Long taskId);

    // MyBatisのInsert戻り値はVoid必須。
    // IDはDBの自動採番。@Options(~)自動でTaskRecord/idに値を入れてもらう
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO tasks (title) VALUES (#{title})")
    void insert(TaskRecord record);
}
