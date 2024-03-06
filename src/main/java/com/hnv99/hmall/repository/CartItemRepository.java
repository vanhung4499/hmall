package com.hnv99.hmall.repository;


import com.hnv99.hmall.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByUserId(Long userId);

    void deleteAllByUserId(Long userId);
}
