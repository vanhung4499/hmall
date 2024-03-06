package com.hnv99.hmall.serivce;

import com.hnv99.hmall.model.entity.OrderItem;

public interface OrderItemService {
    OrderItem addOrderItem(OrderItem orderItem);
    OrderItem getOrderItem(Long id);
    OrderItem updateOrderItem(OrderItem orderItem);
    void deleteOrderItem(Long id);
}
