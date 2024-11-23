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


import lombok.Value;

import java.time.LocalDateTime;

// @Valueは Immutableなクラス自動生成

@Value
public class SampleDTO {
    String content;
    LocalDateTime timestamp;
}
