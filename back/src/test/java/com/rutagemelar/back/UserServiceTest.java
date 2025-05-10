package com.rutagemelar.back;

import com.rutagemelar.back.model.User;
import com.rutagemelar.back.repository.UserRepository;
import com.rutagemelar.back.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import java.util.Optional;

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
    @Test
    void lanzarErrorSiEmailYaExiste() {
        //Arrange
        User usuariaExistente = User.builder()
                .nombre("Ana")
                .email("ana@gmail.com")
                .password("123456")
                .build();

        when(userRepository.findByEmail("ana@gmail.com"))
                .thenReturn(Optional.of(usuariaExistente));

        User nuevaUsuaria = User.builder()
                .nombre("Ana 2")
                .email("Ana@gmail.com") //mismo email
                .password("8788585")
                .build();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registrarUsuaria(nuevaUsuaria);
        });

        assertEquals("El email ya está en uso", exception.getMessage());

        verify(userRepository, never()).save(any());

    }
}
