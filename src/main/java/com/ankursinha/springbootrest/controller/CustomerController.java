package com.ankursinha.springbootrest.controller;

import com.ankursinha.springbootrest.dto.CustomerRequest;
import com.ankursinha.springbootrest.dto.CustomerResponse;
import com.ankursinha.springbootrest.dto.LoginRequest;
import com.ankursinha.springbootrest.dto.LoginResponse;
import com.ankursinha.springbootrest.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        System.out.println("Test endpoint hit!");
        return ResponseEntity.ok("Test successful!");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        LoginResponse response = customerService.login(request);
        return ResponseEntity.ok(response);
    }
}