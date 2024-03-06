package com.hnv99.hmall.serivce;

import com.hnv99.hmall.model.entity.Order;

public interface OrderService {
    Order createOrder();
    Order getOrder(Long id);
    Order updateOrder(Order order);
    void deleteOrder(Long id);
}
