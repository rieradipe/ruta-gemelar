package com.rutagemelar.back;

import com.rutagemelar.back.model.User;
import com.rutagemelar.back.repository.UserRepository;
import com.rutagemelar.back.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class UserServiceTest {
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }
    @Test
    void debeRegistrarUsuariaCorrectamente() {
        User usuaria  = User.builder()
                .nombre("Ana")
                .email("ana@email.com")
                .password("segura123")
                .build();
        when(userRepository.save(any(User.class))).thenReturn(usuaria);

        //Act
        User resultado = userService.registrarUsuaria(usuaria);

        //Assert
        assertNotNull(resultado);
        assertEquals("Ana", resultado.getNombre());

        verify(userRepository, times(1)).save(usuaria);

        //sin conexion aun a la bbdd
        //assertNotNull(user);
        //assertEquals("Ana",user.getNombre());
    }
}
