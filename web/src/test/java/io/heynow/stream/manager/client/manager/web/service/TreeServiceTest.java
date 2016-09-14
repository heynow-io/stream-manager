package io.heynow.stream.manager.client.manager.web.service;

import io.heynow.stream.manager.client.manager.TestsBase;
import io.heynow.stream.manager.client.web.domain.Observable;
import io.heynow.stream.manager.client.web.service.TreeService;
import io.heynow.stream.manager.client.web.domain.Node;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TreeServiceTest extends TestsBase {

    @Test
    public void shouldFindLeafs() {
        TreeService treeService = new TreeService();

        List<Node> leafs = treeService.findLeafs(buildTree());

        assertThat(leafs).hasSize(2).extracting("id").contains(40L, 5L);
    }

    @Test
    public void shouldFindPathFromLeafToRoot() {
        TreeService treeService = new TreeService();

        Observable observable = new Observable();
        observable.setSource("queue");
        observable.setEventType("something");

        List<Node> pathFromLeaf = treeService.findPathFromLeaf(buildTree(), observable);

        assertThat(pathFromLeaf).hasSize(5).extracting("id").contains(5L, 41L, 31L, 2L, 1L);
    }
}
