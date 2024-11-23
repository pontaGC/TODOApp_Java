package com.example.todo_api.repository.sample;

import org.springframework.stereotype.Repository;

@Repository
class SampleRepositoryImpl implements SampleRepository{

    @Override
    public SampleRecord select() {
        return new SampleRecord("Hello World!");
    }
}
