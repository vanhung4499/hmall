package com.hnv99.hmall.converter;

import com.hnv99.hmall.model.entity.CartItem;
import com.hnv99.hmall.model.entity.OrderItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemConverter {
    OrderItem cartItem2OrderItem(CartItem cartItem);

    List<OrderItem> cartItem2OrderItem(List<CartItem> cartItems);
}
