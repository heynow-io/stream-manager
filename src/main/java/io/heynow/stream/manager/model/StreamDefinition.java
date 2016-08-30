package io.heynow.stream.manager.model;

import lombok.Data;

@Data
public class StreamDefinition {

    private String id;
    private Operator rootConsumer;
}
