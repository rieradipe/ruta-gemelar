package com.rutagemelar.back.service;

import com.rutagemelar.back.model.User;
import com.rutagemelar.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

public User registrarUsuaria (User usuaria) {
    System.out.println("Hola autobuild");

    if (userRepository.findByEmail(usuaria.getEmail()).isPresent()) {
        throw new IllegalArgumentException("El email ya está en uso");
    }
    String passwordEncriptada = passwordEncoder.encode(usuaria.getPassword());
    usuaria.setPassword(passwordEncriptada);

   return userRepository.save(usuaria);
}}