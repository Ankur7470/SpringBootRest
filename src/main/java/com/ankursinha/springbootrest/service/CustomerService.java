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
}