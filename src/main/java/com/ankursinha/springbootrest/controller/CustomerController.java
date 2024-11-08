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

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        System.out.println("Test endpoint hit!");
        return ResponseEntity.ok("Test successful!");
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest request) {
        CustomerResponse response = customerService.createCustomer(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        LoginResponse response = customerService.login(request);
        return ResponseEntity.ok(response);
    }
}