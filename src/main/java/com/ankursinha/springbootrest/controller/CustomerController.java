package com.ankursinha.springbootrest.controller;

import com.ankursinha.springbootrest.dto.CustomerRequest;
import com.ankursinha.springbootrest.dto.CustomerResponse;
import com.ankursinha.springbootrest.dto.LoginRequest;
import com.ankursinha.springbootrest.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController()
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    //endpoint for testing
    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        System.out.println("Test endpoint hit!");
        return ResponseEntity.ok("Test successful!");
    }

    //Endpoint to create a new Customer
    @PostMapping("/register")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest request) {
        CustomerResponse response = customerService.register(request);
        return ResponseEntity.ok(response);
    }

    //Endpoint for Login of a customer
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        String response = customerService.login(request);
        return ResponseEntity.ok(response);
    }

    //Endpoint to get customer detail
    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable String email) {
        CustomerResponse response = customerService.retrieveCustomer(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

// //   Endpoint to update customer details
//    @PutMapping("/{id}")
//    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerRequest request) {
//        CustomerResponse response = customerService.updateCustomer(id, request);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//
// //   Endpoint to delete a customer
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
//        String response = customerService.deleteCustomer(id);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}