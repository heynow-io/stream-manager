package io.heynow.stream.manager.model;

import lombok.Data;

@Data
public class ProcessingModel {

    private String streamId;
    private Consumer firstConsumer;
}
