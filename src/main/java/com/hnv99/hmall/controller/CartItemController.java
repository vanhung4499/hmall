package com.hnv99.hmall.controller;

import com.hnv99.hmall.common.core.result.Result;
import com.hnv99.hmall.model.entity.CartItem;
import com.hnv99.hmall.serivce.CartItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cart Item Controller")
@AllArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartItemController {
    private final CartItemService cartItemService;

    @Operation(summary = "Add a cart item")
    @PostMapping("/add")
    public Result addCartItem(@RequestBody CartItem cartItem) {
        CartItem result = cartItemService.addCartItem(cartItem);
        return Result.success(result);
    }

    @Operation(summary = "Get all cart items")
    @GetMapping("/")
    public Result getAllCartItems() {

        return Result.success(cartItemService.getAllCartItems());
    }

    @Operation(summary = "Update a cart item")
    @PatchMapping("/")
    public Result updateCartItem(@RequestBody CartItem cartItem) {
        CartItem result = cartItemService.updateCartItem(cartItem);
        return Result.success(result);
    }

    @Operation(summary = "Delete a cart item")
    @DeleteMapping("/{id}")
    public Result deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
        return Result.success();
    }

    @Operation(summary = "Delete all cart items")
    @DeleteMapping("/")
    public Result deleteAllCartItems() {
        cartItemService.deleteAllCartItems();
        return Result.success();
    }
}
