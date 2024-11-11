package com.ankursinha.springbootrest.controller;

import com.ankursinha.springbootrest.dto.CustomerRequest;
import com.ankursinha.springbootrest.dto.CustomerResponse;
import com.ankursinha.springbootrest.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

    //Endpoint to create a new Customer
    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest request) {
        CustomerResponse response = customerService.createCustomer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Endpoint to get customer detail
    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable String email) {
        CustomerResponse response = customerService.retrieveCustomer(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoint to update customer details
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerRequest request) {
        CustomerResponse response = customerService.updateCustomer(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // Endpoint to delete a customer
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        String response = customerService.deleteCustomer(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}