package com.rutagemelar.back.service;
import com.rutagemelar.back.model.RegisterRequest;
import com.rutagemelar.back.model.User;
import com.rutagemelar.back.repository.UserRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

public User registrarUsuaria (RegisterRequest request) {
    System.out.println("Hola pesadilla");

    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
        throw new IllegalArgumentException("El email ya está en uso");
    }
    String passwordEncriptada = passwordEncoder.encode(request.getPassword());
    User usuaria = User.builder()
            .nombre(request.getNombre())
            .email(request.getEmail())
            .password(passwordEncriptada)
            .tipoEmbarazoGemelar(request.getTipoEmbarazoGemelar())
            .fechaProbableParto(LocalDate.parse(request.getFechaProbableParto()))
            .build();


    return userRepository.save(usuaria);
}}