package com.hnv99.hmall.controller;

import com.hnv99.hmall.common.core.result.Result;
import com.hnv99.hmall.model.entity.User;
import com.hnv99.hmall.serivce.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Management")
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get a user by its ID")
    public Result getUser(@Schema(description = "User ID") Long id) {
        User user = userService.getUser(id);
        return Result.success(user);
    }

    @Operation(summary = "Update a user by its ID")
    @PatchMapping("/{id}")
    public Result updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return Result.success(updatedUser);
    }

    @Operation(summary = "Delete a user by its ID")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        Result.judge(true);
    }

    @Operation(summary = "Get all users")
    @GetMapping("/all")
    public Result<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return Result.success(users);
    }
}
