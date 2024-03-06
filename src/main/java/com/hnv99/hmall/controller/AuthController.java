package com.hnv99.hmall.controller;

import com.hnv99.hmall.common.core.result.Result;
import com.hnv99.hmall.model.form.UserLoginForm;
import com.hnv99.hmall.model.form.UserRegisterForm;
import com.hnv99.hmall.serivce.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth Management")
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterForm formData) {
        boolean result = authService.register(formData);
        return Result.judge(result);
    }

    @Operation(summary = "Login")
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginForm formData) {
        String token = authService.login(formData);
        return Result.success(token);
    }

    @Operation(summary = "Logout")
    @PostMapping("/logout")
    public Result logout() {
        authService.logout();
        return Result.success(null);
    }
}
