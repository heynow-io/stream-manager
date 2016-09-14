package io.heynow.stream.manager.client.web.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Property {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String value;
}
