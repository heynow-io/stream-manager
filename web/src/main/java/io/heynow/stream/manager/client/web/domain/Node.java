package io.heynow.stream.manager.client.web.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Collections;
import java.util.List;

@Data
@Entity
public class Node {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Operator operator;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Node> parents = Collections.emptyList();

}
