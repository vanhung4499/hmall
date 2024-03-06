package com.hnv99.hmall.serivce.impl;

import com.hnv99.hmall.common.security.util.SecurityUtils;
import com.hnv99.hmall.converter.OrderItemConverter;
import com.hnv99.hmall.model.entity.CartItem;
import com.hnv99.hmall.model.entity.Order;
import com.hnv99.hmall.repository.CartItemRepository;
import com.hnv99.hmall.repository.OrderItemRepository;
import com.hnv99.hmall.repository.OrderRepository;
import com.hnv99.hmall.serivce.OrderService;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository catItemRepository;
    private final OrderItemConverter orderItemConverter;

    @Override
    public Order createOrder() {
        Long userId = SecurityUtils.getUserId();
        Order order = new Order();
        order.setUserId(userId);

        List<CartItem> items = catItemRepository.findAllByUserId(userId);
        order.setItems(orderItemConverter.cartItem2OrderItem(items));

        Order savedOrder = orderRepository.save(order);

        catItemRepository.deleteAllByUserId(userId);

        return savedOrder;
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
        orderItemRepository.deleteAllByOrderId(id);
    }
}
