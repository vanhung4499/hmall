package com.hnv99.hmall.serivce;

import com.hnv99.hmall.model.entity.CartItem;

import java.util.List;

public interface CartItemService {
    CartItem addCartItem(CartItem cartItem);
    List<CartItem> getAllCartItems();
    CartItem updateCartItem(CartItem cartItem);
    void deleteCartItem(Long id);
    void deleteAllCartItems();
}
