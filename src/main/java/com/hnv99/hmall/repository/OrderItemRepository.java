package com.hnv99.hmall.repository;

import com.hnv99.hmall.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    void deleteAllByOrderId(Long id);
}
