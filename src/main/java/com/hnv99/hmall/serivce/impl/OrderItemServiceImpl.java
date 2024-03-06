package com.hnv99.hmall.serivce.impl;

import com.hnv99.hmall.model.entity.OrderItem;
import com.hnv99.hmall.repository.OrderItemRepository;
import com.hnv99.hmall.serivce.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    @Override
    public OrderItem addOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem getOrderItem(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    @Override
    public OrderItem updateOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}
