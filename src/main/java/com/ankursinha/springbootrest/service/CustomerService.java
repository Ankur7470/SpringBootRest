package com.ankursinha.springbootrest.service;

import com.ankursinha.springbootrest.dto.CustomerRequest;
import com.ankursinha.springbootrest.dto.CustomerResponse;
import com.ankursinha.springbootrest.entity.Customer;
import com.ankursinha.springbootrest.exception.CustomerNotFoundException;
import com.ankursinha.springbootrest.helper.EncryptionService;
import com.ankursinha.springbootrest.helper.JWTValidationService;
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
    private final EncryptionService encryptionService;
//    private final JWTValidationService jwtValidationService;

    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        customer.setPassword(encryptionService.encode(request.password()));
        Customer savedCustomer = repo.save(customer);
        return mapper.toDto(savedCustomer);
    }

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = repo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot retrieve Customer:: No customer found with the provided Email:: %s", email)
                ));

        return mapper.toDto(customer);
    }

    public CustomerResponse updateCustomer(Long id, @Valid CustomerRequest request) {
        Optional<Customer> customerOpt = repo.findById(id);

        if (customerOpt.isEmpty()) {
            throw new CustomerNotFoundException(
                    format("Cannot update Customer:: No customer found with the provided ID:: %s", id)
            );
        }

        Customer customer = customerOpt.get();
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setPassword(encryptionService.encode(request.password()));
        Customer updatedCustomer = repo.save(customer);

        return mapper.toDto(updatedCustomer);
    }

    public String deleteCustomer(Long id) {
        Customer customer = repo.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot delete Customer:: No customer found with the provided ID:: %s", id)
                ));

        repo.delete(customer);
        return "Customer deleted successfully";
    }
}