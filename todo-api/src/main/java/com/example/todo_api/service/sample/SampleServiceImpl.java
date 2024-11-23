package com.example.todo_api.service.sample;

import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

    @Override
    public SampleEntity find() {
        return new SampleEntity("Hello");
    }
}
