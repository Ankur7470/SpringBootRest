package com.ankursinha.springbootrest.helper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EncryptionService {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean validates(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
