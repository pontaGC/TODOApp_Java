package com.example.todo_api.controller.sample;

// DTO: Data Transfer Object
// 今回はSpringを使用して、フィールドをResponse用のJSONを作成する用のオブジェクト
//
// Response body 例;
// {
//    "content": "content",
//    "timestamp": "2024-11-23T21:16:12.7893401"
// }
//

import java.time.LocalDateTime;

public class SampleDTO {
    private String content;
    private LocalDateTime timestamp;

    public SampleDTO(String content, LocalDateTime timestamp) {
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
