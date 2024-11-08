package com.ankursinha.springbootrest.service;

import com.ankursinha.springbootrest.dto.CustomerRequest;
import com.ankursinha.springbootrest.dto.CustomerResponse;
import com.ankursinha.springbootrest.entity.Customer;
import com.ankursinha.springbootrest.exception.CustomerNotFoundException;
import com.ankursinha.springbootrest.mapper.CustomerMapper;
import com.ankursinha.springbootrest.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;

    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        Customer savedCustomer = repo.save(customer);
        return mapper.toDto(savedCustomer);
    }

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = repo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
                ));
        return mapper.toDto(customer);
    }

    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        Customer existingCustomer = repo.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id));

        // Update fields (excluding email and password)
        existingCustomer.setFirstName(request.firstName());
        existingCustomer.setLastName(request.lastName());
        existingCustomer.setEmail(request.email());
        existingCustomer.setPassword(request.password());

        Customer updatedCustomer = repo.save(existingCustomer);
        return mapper.toDto(updatedCustomer);
    }

    public String deleteCustomer(Long id) {
        Customer customer = repo.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id));
        repo.delete(customer);
        return "Customer with ID: " + id + " deleted successfully.";
    }
}