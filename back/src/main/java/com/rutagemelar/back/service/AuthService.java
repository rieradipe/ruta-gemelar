package com.rutagemelar.back.service;

import com.rutagemelar.back.model.AuthResponse;
import com.rutagemelar.back.model.LoginRequest;
import com.rutagemelar.back.model.User;
import com.rutagemelar.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request) {
        //implementar despues del TDD
        return null;
    }
}
