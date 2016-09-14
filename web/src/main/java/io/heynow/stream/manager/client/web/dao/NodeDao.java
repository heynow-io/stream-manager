package io.heynow.stream.manager.client.web.dao;

import io.heynow.stream.manager.client.web.domain.Node;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeDao extends JpaRepository<Node, Long> {

}
