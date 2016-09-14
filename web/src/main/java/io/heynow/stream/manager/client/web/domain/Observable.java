package io.heynow.stream.manager.client.web.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(indexes = @Index(columnList = Observable.SOURCE + "," + Observable.EVENT_TYPE))
public class Observable {

    public static final String SOURCE = "source";
    public static final String EVENT_TYPE = "eventType";

    @Id
    @GeneratedValue
    private Long id;

    private String source;
    private String eventType;

    @ManyToOne
    private Stream stream;

}
