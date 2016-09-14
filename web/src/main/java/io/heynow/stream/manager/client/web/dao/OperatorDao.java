package io.heynow.stream.manager.client.web.dao;

import io.heynow.stream.manager.client.web.domain.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatorDao extends JpaRepository<Operator, Long> {

}
