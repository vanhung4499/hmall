package com.hnv99.hmall.serivce.impl;

import com.hnv99.hmall.common.security.util.SecurityUtils;
import com.hnv99.hmall.model.entity.CartItem;
import com.hnv99.hmall.repository.CartItemRepository;
import com.hnv99.hmall.serivce.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartItem addCartItem(CartItem cartItem) {
        Long userId = SecurityUtils.getUserId();
        cartItem.setUserId(userId);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> getAllCartItems() {
        Long userId = SecurityUtils.getUserId();
        return cartItemRepository.findAllByUserId(userId);
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public void deleteAllCartItems() {
        Long userId = SecurityUtils.getUserId();
        cartItemRepository.deleteAllByUserId(userId);
    }
}