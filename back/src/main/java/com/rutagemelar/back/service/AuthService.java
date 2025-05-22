package com.rutagemelar.back.service;

import java.time.LocalDate;
import com.rutagemelar.back.model.AuthResponse;
import com.rutagemelar.back.model.LoginRequest;
import com.rutagemelar.back.model.RegisterRequest;
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

    public void registerUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está en uso");
        }

        String passwordEncriptada = passwordEncoder.encode(request.getPassword());

        LocalDate fechaParto = LocalDate.parse(request.getFechaProbableParto());
        if (fechaParto.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha probable de parto debe ser una fecha futura.");
        }

        User user = User.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(passwordEncriptada)
                .fechaProbableParto(fechaParto)
                .tipoEmbarazoGemelar(request.getTipoEmbarazoGemelar())
                .build();


        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }
}