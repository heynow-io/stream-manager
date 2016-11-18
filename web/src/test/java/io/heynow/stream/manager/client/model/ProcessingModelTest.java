package io.heynow.stream.manager.client.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import io.heynow.stream.manager.client.manager.TestsBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class ProcessingModelTest extends TestsBase {
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void processingModelCanBeSerialized() throws JsonProcessingException {
        ProcessingModel processingModel = givenProcessingModel();

        String serializedProcessingModel = objectMapper.writeValueAsString(processingModel);

        assertThat(serializedProcessingModel).isNotEmpty();
    }

    private ProcessingModel givenProcessingModel() {
        ProcessingModel processingModel = new ProcessingModel();
        processingModel.setConsumers(ImmutableList.of(new Consumer(1L, "consumer1"), new Consumer(2L, "consumer2")));
        return processingModel;
    }
}