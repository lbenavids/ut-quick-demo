package com.cleveritgroup.utquickdemo.todo.controller;


import com.cleveritgroup.utquickdemo.todo.application.TodoService;
import com.cleveritgroup.utquickdemo.todo.domain.Todo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @PostMapping
    public Todo save(@RequestBody String description) {
        return todoService.newTodo(description);
    }


}
