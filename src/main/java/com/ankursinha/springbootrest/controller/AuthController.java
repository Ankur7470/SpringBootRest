package com.ankursinha.springbootrest.controller;

import com.ankursinha.springbootrest.dto.LoginRequest;
import com.ankursinha.springbootrest.dto.LoginResponse;
import com.ankursinha.springbootrest.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthController {

    private final CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        LoginResponse response = customerService.login(request);
        return ResponseEntity.ok(response);
    }
}