package io.heynow.stream.manager.client.manager;

import com.google.common.collect.Sets;
import io.heynow.stream.manager.client.StreamManagerApplication;
import io.heynow.stream.manager.client.web.domain.Node;
import io.heynow.stream.manager.client.web.domain.Operator;
import io.heynow.stream.manager.client.web.domain.Property;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StreamManagerApplication.class)
@WebAppConfiguration
public class TestsBase {

    protected Node buildTree() {
        return buildNode(1L, buildOperator("mailer"), Sets.newHashSet(
                buildNode(2L, buildOperator("merger", asList(buildProperty("key", "foo.bar"))), Sets.newHashSet(
                        buildNode(30L, buildOperator("filter", asList(buildProperty("foo", ">5"))), Sets.newHashSet(
                                buildNode(40L, buildOperator("queue", "different"))
                        )),
                        buildNode(31L, buildOperator("mapper"), Sets.newHashSet(
                                buildNode(41L, buildOperator("buffer", asList(buildProperty("time", "5"))), Sets.newHashSet(
                                        buildNode(5L, buildOperator("queue", "something"))
                                ))
                        ))
                ))
        ));
    }

    protected Node buildNewTree() {
        return buildNode(null, buildOperator("mailer"), Sets.newHashSet(
                buildNode(null, buildOperator("merger", asList(buildProperty("key", "foo.bar"))), Sets.newHashSet(
                        buildNode(null, buildOperator("filter", asList(buildProperty("foo", ">5"))), Sets.newHashSet(
                                buildNode(null, buildOperator("queue", "different"))
                        )),
                        buildNode(null, buildOperator("mapper"), Sets.newHashSet(
                                buildNode(null, buildOperator("buffer", asList(buildProperty("time", "5"))), Sets.newHashSet(
                                        buildNode(null, buildOperator("queue", "something"))
                                ))
                        ))
                ))
        ));
    }

    protected <T> List<T> asList(T... things) {
        ArrayList<T> objects = new ArrayList<>();
        for (T thing : things) {
            objects.add(thing);
        }
        return objects;
    }

    protected Operator buildOperator(String name, List<Property>... properties) {
        Operator operator = new Operator();
        operator.setName(name);
        if (properties.length == 1) {
            operator.setProperties(properties[0]);
        }
        return operator;
    }

    protected Operator buildOperator(String source, String eventType) {
        Operator operator = new Operator();
        operator.setEventSource(source);
        operator.setEventType(eventType);
        return operator;
    }

    protected Property buildProperty(String name, String value) {
        Property property = new Property();
        property.setName(name);
        property.setValue(value);
        return property;
    }

    protected Node buildNode(Long id, Operator operator) {
        return buildNode(id, operator, Collections.emptySet());
    }

    protected Node buildNode(Long id, Operator operator, Set<Node> parents) {
        Node node = new Node();
        node.setId(id);
        node.setParents(parents);
        node.setOperator(operator);
        return node;
    }

}
