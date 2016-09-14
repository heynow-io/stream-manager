package io.heynow.stream.manager.client.web.dao;

import io.heynow.stream.manager.client.web.domain.Stream;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamDao extends JpaRepository<Stream, Long> {
    
}