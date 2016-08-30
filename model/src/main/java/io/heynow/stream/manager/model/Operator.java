package io.heynow.stream.manager.model;

import lombok.Data;

import java.util.List;

@Data
public class Operator {

    private String name;
    private List<Property> properties;
    private List<Operator> parents;
}
