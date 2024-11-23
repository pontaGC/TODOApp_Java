package com.example.todo_api.service.sample;

import com.example.todo_api.repository.sample.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class SampleServiceImpl implements SampleService {

    private final SampleRepository repostory;

    @Override
    public SampleEntity find() {
        var sampleRecord = this.repostory.select();
        return new SampleEntity(sampleRecord.getContent());
    }
}
