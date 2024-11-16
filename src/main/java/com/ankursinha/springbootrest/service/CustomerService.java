package com.ankursinha.springbootrest.service;

import com.ankursinha.springbootrest.dto.CustomerRequest;
import com.ankursinha.springbootrest.dto.CustomerResponse;
import com.ankursinha.springbootrest.dto.LoginRequest;
import com.ankursinha.springbootrest.entity.Customer;
import com.ankursinha.springbootrest.exception.CustomerNotFoundException;
import com.ankursinha.springbootrest.helper.EncryptionService;
import com.ankursinha.springbootrest.helper.JWTHelper;
import com.ankursinha.springbootrest.mapper.CustomerMapper;
import com.ankursinha.springbootrest.repo.CustomerRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import static java.lang.String.format;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo repo;

    @Autowired
    CustomerMapper mapper;

    @Autowired
    EncryptionService encryptionService;

    @Autowired
    JWTHelper jwtHelper;

    @Autowired
    AuthenticationManager authManager;

    public CustomerResponse register(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        Customer savedCustomer = repo.save(customer);
        return mapper.toDto(savedCustomer);
    }

    public String login(LoginRequest request) {
        Customer customer = repo.findByEmail(request.email());
        if (customer == null) {
            throw new CustomerNotFoundException(format("Customer with email %s not found", request.email()));
        }
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        if(auth.isAuthenticated())
            return jwtHelper.generateToken(request.email());

        return "Wrong Username or Password";
    }

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = repo.findByEmail(email);
        if(customer == null) {
            throw new CustomerNotFoundException(format("Customer with email %s not found", email));
        }
        return mapper.toDto(customer);
    }


//    public CustomerResponse updateCustomer(Long id, @Valid CustomerRequest request) {
//        Customer customerN = mapper.toEntity(request);
//
//        Customer customer = repo.getById(id);
//
//        if(customer == null) {
//            throw new CustomerNotFoundException(format("Customer with id %s not found", id));
//        }
//
//        customer.setFirstName(request.firstName());
//        customer.setLastName(request.lastName());
//        customer.setEmail(request.email());
//        customer.setPassword(encryptionService.encode(request.password()));
//        Customer updatedCustomer = repo.save(customer);
//
//        return mapper.toDto(updatedCustomer);
//    }
//
//    public String deleteCustomer(Long id) {
//        Customer customer = repo.getById(id);
//
//        if(customer == null) {
//            throw new CustomerNotFoundException(format("Customer with id %s not found", id));
//        }
//
//        repo.delete(customer);
//        return "Customer deleted successfully";
//    }

}



