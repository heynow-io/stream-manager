package io.heynow.stream.manager.client.web.dao;

import io.heynow.stream.manager.client.web.domain.Observable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObservableDao extends JpaRepository<Observable, Long> {

    List<Observable> findBySourceAndEventType(String source, String eventType);
}
