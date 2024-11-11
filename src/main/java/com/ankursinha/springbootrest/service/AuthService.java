package com.ankursinha.springbootrest.service;

import com.ankursinha.springbootrest.dto.LoginRequest;
//import com.ankursinha.springbootrest.dto.LoginResponse;
import com.ankursinha.springbootrest.entity.Customer;
import com.ankursinha.springbootrest.exception.CustomerNotFoundException;
import com.ankursinha.springbootrest.helper.EncryptionService;
import com.ankursinha.springbootrest.helper.JWTHelper;
import com.ankursinha.springbootrest.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepo repo;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public String login(LoginRequest request) {
        Customer customer = repo.findByEmail(request.email())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", request.email())
                ));

        if(!encryptionService.validates(request.password(), customer.getPassword())) {
            return "Wrong Password or Email";
        }

        return jwtHelper.generateToken(request.email());
    }
}
