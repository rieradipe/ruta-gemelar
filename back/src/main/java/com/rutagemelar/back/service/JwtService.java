package com.rutagemelar.back.service;

import com.rutagemelar.back.model.User;

public class JwtService {
    public String generateToken(User user) {
        return "fake.jwt.token";
    }

    public Long getUserIdFromToken(String token) {
        return 1L;
    }
}
