package org.gdg.zipte.domain.order.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    //TossOrderId로 조회
    Optional<Order> findByTossOrderId(String tossOrderId);

}
