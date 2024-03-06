package com.hnv99.hmall.controller;

import com.hnv99.hmall.model.entity.Order;
import com.hnv99.hmall.serivce.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order", description = "Order management")
@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Create order")
    @PostMapping("/create")
    public Order createOrder() {
        return orderService.createOrder();
    }
}
