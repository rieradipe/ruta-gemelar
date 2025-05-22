package com.rutagemelar.back;

import com.rutagemelar.back.model.User;
import com.rutagemelar.back.repository.UserRepository;
import com.rutagemelar.back.service.UserService;
import lombok.Builder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.rutagemelar.back.service.JwtService;
import com.rutagemelar.back.model.RegisterRequest;



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

            RegisterRequest request = RegisterRequest.builder()
                    .nombre("Ana")
                    .email("ana@email.com")
                    .password("segura123")
                    .tipoEmbarazoGemelar("No")
                    .fechaProbableParto("2025-08-15")
                    .build();

            User resultado = userService.registrarUsuaria(request);


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
        // Arrange - Creamos una usuaria que ya existe en el sistema
        User usuariaExistente = User.builder()
                .nombre("Ana")
                .email("ana@gmail.com")
                .password("123456")
                .build();

        when(userRepository.findByEmail("ana@gmail.com"))
                .thenReturn(Optional.of(usuariaExistente));

        // Creamos una nueva solicitud con el mismo email
        RegisterRequest nuevaRequest = RegisterRequest.builder()
                .nombre("Ana 2")
                .email("ana@gmail.com") // mismo email
                .password("8788855")
                .tipoEmbarazoGemelar("No")
                .fechaProbableParto("2025-08-15")
                .build();

        // Act & Assert - Esperamos que se lance una excepción
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registrarUsuaria(nuevaRequest);
        });

        assertEquals("El email ya está en uso", exception.getMessage());

        verify(userRepository, never()).save(any());
    }

    @Test
        void debeEncriptarLaPasswordAntesDeGuardar () {
            //Arrange
            RegisterRequest nuevaRequest = RegisterRequest.builder()
                    .nombre("Ana")
                    .email("ana@email.com")
                    .password("123456")
                    .tipoEmbarazoGemelar("No")
                    .fechaProbableParto("2025-08-15")
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
            User resultado = userService.registrarUsuaria(nuevaRequest);

            //ASSERT - comprobaos que la contraseña a cambiado y es la eperada
            assertEquals("hashed123", resultado.getPassword());
            assertNotEquals("123456", resultado.getPassword());
        }

    @Test
    void debeGenerarTokenAlRegistrarUsuaria() {
        when(jwtService.generateToken(any(User.class)))
                .thenReturn("fake.jwt.token");

        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        RegisterRequest usuaria = RegisterRequest.builder()
                .nombre("Ana")
                .email("ana@email.com")
                .password("segura123")
                .tipoEmbarazoGemelar("No")
                .fechaProbableParto("2025-08-15")
                .build();

        // ACT
        User resultado = userService.registrarUsuaria(usuaria);

        // ASSERT
        verify(jwtService, times(1)).generateToken(resultado);
    }

}
