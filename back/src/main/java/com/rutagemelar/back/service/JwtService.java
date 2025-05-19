package com.rutagemelar.back.service;

import com.rutagemelar.back.model.User;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    public String generateToken(User user) {
        return "fake.jwt.token";
    }

    public Long getUserIdFromToken(String token) {
        return 1L;
    }
}
