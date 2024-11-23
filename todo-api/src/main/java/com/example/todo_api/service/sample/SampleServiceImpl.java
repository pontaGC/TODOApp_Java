package com.example.todo_api.service.sample;

public class SampleServiceImpl implements SampleService {

    @Override
    public SampleEntity find() {
        return new SampleEntity("Hello");
    }
}
