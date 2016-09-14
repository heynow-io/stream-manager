package io.heynow.stream.manager.client.model;

import lombok.Data;

import java.util.Map;

@Data
public class Note {

    private ProcessingModel processingModel;
    private Map<String, Object> payload;

}
