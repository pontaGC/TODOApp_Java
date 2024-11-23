package com.example.todo_api.controller.sample;

import com.example.todo_api.service.sample.SampleService;
import com.example.todo_api.service.sample.SampleServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/samples")
public class SampleController {

    private final SampleService sampleService = new SampleServiceImpl();

    @GetMapping
    public SampleDTO index(){
        var entity = this.sampleService.find();
        return new SampleDTO(entity.getContent(), LocalDateTime.now());
    }
}
