package io.heynow.stream.manager.client.web.service;

import io.heynow.stream.manager.client.web.domain.Node;
import io.heynow.stream.manager.client.web.domain.Observable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class TreeService {

    public List<Node> findLeafs(Node root) {
        if (root.getParents().isEmpty()) {
            return asList(root);
        } else {
            return root.getParents().stream()
                    .flatMap(parent -> this.findLeafs(parent).stream())
                    .collect(toList());
        }
    }

    public void walkTreeFromLeafs(Node root, Consumer<Node> nodeConsumer) {
        if (!root.getParents().isEmpty()) {
            for (Node parent : root.getParents()) {
                walkTreeFromLeafs(parent, nodeConsumer);
            }
        }
        nodeConsumer.accept(root);
    }

    public List<Node> findPathFromLeaf(Node root, Observable leafData) {
        if (matches(root, leafData)) {
            return asList(root);
        } else if (root.getParents().isEmpty()) {
            return Collections.emptyList();
        } else {
            for (Node parent : root.getParents()) {
                List<Node> pathFromLeaf = findPathFromLeaf(parent, leafData);
                if (!pathFromLeaf.isEmpty()) {
                    return Stream.concat(pathFromLeaf.stream(), asList(root).stream()).collect(toList());
                }
            }
            return Collections.emptyList();
        }
    }

    private boolean matches(Node root, Observable leafData) {
        return root.getOperator() != null &&
                leafData.getEventType().equals(root.getOperator().getEventType()) &&
                leafData.getSource().equals(root.getOperator().getEventSource());
    }
}
