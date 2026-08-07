package dev.codeio.HelloWorld.service;

import dev.codeio.HelloWorld.models.Todo;
import dev.codeio.HelloWorld.models.User;
import dev.codeio.HelloWorld.repository.TodoRepository;
import dev.codeio.HelloWorld.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Todo createTodo(Todo todo, String email) {
        User user = getUserByEmail(email);
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    public Todo getTodoById(Long id, String email) {
        User user = getUserByEmail(email);
        return todoRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Todo Not Found"));
    }

    public Page<Todo> getAllTodosPages(int page, int size, String email) {
        User user = getUserByEmail(email);
        Pageable pageable = PageRequest.of(page, size);
        return todoRepository.findByUser(user, pageable);
    }

    public List<Todo> getTodos(String email) {
        User user = getUserByEmail(email);
        return todoRepository.findByUser(user);
    }

    public Todo updateTodo(Todo todo, String email) {
        // verify the todo belongs to this user before allowing update
        getTodoById(todo.getId(), email);
        User user = getUserByEmail(email);
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    public void deleteTodoById(Long id, String email) {
        Todo todo = getTodoById(id, email);
        todoRepository.delete(todo);
    }
}