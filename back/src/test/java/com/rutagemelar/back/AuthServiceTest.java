package com.rutagemelar.back;

import com.rutagemelar.back.model.LoginRequest;

import com.rutagemelar.back.model.User;
import com.rutagemelar.back.repository.UserRepository;
import com.rutagemelar.back.service.AuthService;
import com.rutagemelar.back.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.mockito.Mockito.mock;

public class AuthServiceTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthService authService;

    @BeforeEach
    void setup () {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtService = mock(JwtService.class);
        authService = new AuthService(userRepository, passwordEncoder, jwtService);
    }
    @Test
    void debeHacerLoginCorrectaente() {
    //ARRANGE
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("ana@gmail.com");
        loginRequest.setPassword("123456");

        User usuaria = User.builder()
                .email("ana@gmail.com")
                .password("hashedPasword")//lo que tenemos guardado en base de datos
                .build();

        //repositorio encuentra una usuaria con ese email
        when(userRepository.findByEmail("ana@gmail.com"))
                .thenReturn(Optional.of(usuaria));

        //el encoder verifica que la contraseña existe
        when(passwordEncoder.matches("123456", "hasheadPassword"))
                .thenReturn(true);

        //simulamos la creacion del token
        when(jwtService.generateToken(usuaria))
                .thenReturn("fake.jwt.token");
    }
    @Test
    void debeFallarLoginConPasswordIncorrecto() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("ana@gmail.com");
        loginRequest.setPassword("123456");

        User usuaria = User.builder()
                .email("ana@gmail.com")
                .password("hashedPasword")//lo que tenemos guardado en base de datos
                .build();

        //repositorio encuentra una usuaria con ese email
        when(userRepository.findByEmail("ana@gmail.com"))
                .thenReturn(Optional.of(usuaria));

        //el encoder verifica que la contraseña es incorrecta
        when(passwordEncoder.matches("123456", "hasheadPassword"))
                .thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            authService.login(loginRequest);
        });
        assertEquals("Credenciales inválidas", exception.getMessage());

    }
}
