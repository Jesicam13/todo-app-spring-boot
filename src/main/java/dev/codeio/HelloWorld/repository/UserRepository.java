package dev.codeio.HelloWorld.repository;

import dev.codeio.HelloWorld.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.OptionalInt;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}

