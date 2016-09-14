package io.heynow.stream.manager.client.web.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.List;

@Data
@Entity
public class Operator {

    @Id
    @GeneratedValue
    private String id;

    private String name;
    private String eventSource;
    private String eventType;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Property> properties = Collections.emptyList();
}
