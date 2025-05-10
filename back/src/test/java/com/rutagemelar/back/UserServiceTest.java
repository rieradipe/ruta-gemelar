package com.rutagemelar.back;

import com.rutagemelar.back.model.User;
import com.rutagemelar.back.repository.UserRepository;
import com.rutagemelar.back.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.rutagemelar.back.service.JwtService;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class UserServiceTest {
    private UserRepository userRepository;
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;


    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtService = mock(JwtService.class);
        userService = new UserService(userRepository, passwordEncoder, jwtService);

    }
        @Test
        void debeRegistrarUsuariaCorrectamente () {
            User usuaria = User.builder()
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
        void lanzarErrorSiEmailYaExiste () {
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
        @Test
        void debeEncriptarLaPasswordAntesDeGuardar () {
            //Arrange
            User usuaria = User.builder()
                    .nombre("Ana")
                    .email("ana@email.com")
                    .password("123456")
                    .build();
            //simulamos que aun no existe
            when(userRepository.findByEmail("ana@email.com"))
                    .thenReturn(Optional.empty());
            //simulamos que el encoder devuelve version encriptada
            when(passwordEncoder.encode("123456"))
                    .thenReturn("hashed123");

            //el repo nos devuelve la misma usuaria que recibe
            when(userRepository.save(any(User.class)))
                    .thenAnswer(invocation -> invocation.getArgument(0));

            //ACT -llamamos al metodo real
            User resultado = userService.registrarUsuaria(usuaria);

            //ASSERT - comprobaos que la contraseña a cambiado y es la eperada
            assertEquals("hashed123", resultado.getPassword());
            assertNotEquals("123456", resultado.getPassword());
        }

        @Test
        void debeGenerarTokenAlRegistrarUsuaria () {
            //Arrange
            User usuaria = User.builder()
                    .nombre("Ana")
                    .email("ana@email.com")
                    .password("123456")
                    .build();
            //simulams que email no esta registrado
            when(userRepository.findByEmail("ana@gmail.com"))
                    .thenReturn(Optional.empty());

            //simulamos que encript la constraseña
            when(passwordEncoder.encode("123456"))
                    .thenReturn("hashed123");

            //simulamos que generamos el token
            when(jwtService.generateToken(any(User.class)))
                    .thenReturn("fake.jwt.token");

            //simulamos que el reposotori devuelve el mismo usurio que se le pasa
            when(userRepository.save(any(User.class)))
                    .thenAnswer(invocation -> invocation.getArgument(0));

            //ACT
            User resultado = userService.registrarUsuaria(usuaria);

            //ASSERT
            verify(jwtService, times(1)).generateToken(resultado);

        }
    }
