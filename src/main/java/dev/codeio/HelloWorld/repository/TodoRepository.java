package dev.codeio.HelloWorld.repository;

import dev.codeio.HelloWorld.models.Todo;
import dev.codeio.HelloWorld.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(User user);
    Page<Todo> findByUser(User user, Pageable pageable);
    Optional<Todo> findByIdAndUser(Long id, User user);
}