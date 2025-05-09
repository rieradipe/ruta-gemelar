package com.rutagemelar.back.service;

import com.rutagemelar.back.model.User;
import com.rutagemelar.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registrarUsuaria(User usuaria) {
        return userRepository.save(usuaria);
    }
}
