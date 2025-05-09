package com.rutagemelar.back.repository;


import com.rutagemelar.back.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.rutagemelar.back.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
