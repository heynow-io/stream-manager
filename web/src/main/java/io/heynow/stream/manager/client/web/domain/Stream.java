package io.heynow.stream.manager.client.web.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class Stream {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;

    @OneToOne(cascade = CascadeType.MERGE)
    private Node rootNode;
}
