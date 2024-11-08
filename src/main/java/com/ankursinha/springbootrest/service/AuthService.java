package com.ankursinha.springbootrest.service;

import com.ankursinha.springbootrest.dto.LoginRequest;
import com.ankursinha.springbootrest.dto.LoginResponse;
import com.ankursinha.springbootrest.entity.Customer;
import com.ankursinha.springbootrest.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepo repo;

    public LoginResponse login(LoginRequest request) {
        Optional<Customer> customerOpt = repo.findByEmail(request.email());

        if (customerOpt.isPresent() && customerOpt.get().getPassword().equals(request.password())) {
            return new LoginResponse("Login successful");
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }
}
