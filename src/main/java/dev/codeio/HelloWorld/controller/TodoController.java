package dev.codeio.HelloWorld.controller;

import dev.codeio.HelloWorld.service.TodoService;
import dev.codeio.HelloWorld.models.Todo;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
@Slf4j
public class TodoController {
    @Autowired
    private TodoService todoService;

    @ApiResponses(value = {
            @ApiResponse(responseCode="200",description="Todo Retrieved Successfully"),
            @ApiResponse(responseCode = "404",description="Todo was not found!")
    })
    @GetMapping("/{id}")
    ResponseEntity<Todo> getTodoById(@PathVariable long id, Authentication authentication) {
        try {
            Todo createdTodo = todoService.getTodoById(id, authentication.getName());
            return new ResponseEntity<>(createdTodo, HttpStatus.OK);
        } catch (RuntimeException exception) {
            log.error("Todo not found", exception);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    ResponseEntity<List<Todo>> getTodos(Authentication authentication) {
        return new ResponseEntity<>(todoService.getTodos(authentication.getName()), HttpStatus.OK);
    }

    @GetMapping("/page")
    ResponseEntity<Page<Todo>> getTodosPage(@RequestParam int page, @RequestParam int size, Authentication authentication) {
        return new ResponseEntity<>(todoService.getAllTodosPages(page, size, authentication.getName()), HttpStatus.OK);
    }

    @PostMapping("/create")
    ResponseEntity<Todo> createUser(@RequestBody Todo todo, Authentication authentication) {
        Todo createdTodo = todoService.createTodo(todo, authentication.getName());
        return new ResponseEntity<>(createdTodo, HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<Todo> updateTodoById(@RequestBody Todo todo, Authentication authentication) {
        return new ResponseEntity<>(todoService.updateTodo(todo, authentication.getName()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteTodoById(@PathVariable long id, Authentication authentication) {
        todoService.deleteTodoById(id, authentication.getName());
    }
}