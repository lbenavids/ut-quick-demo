package com.cleveritgroup.utquickdemo.todo.application;

import com.cleveritgroup.utquickdemo.todo.config.TodoProperties;
import com.cleveritgroup.utquickdemo.todo.domain.Todo;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private final TodoSaveRepository todoSaveRepository;
    private final TodoProperties todoProperties;


    public TodoService(TodoSaveRepository todoSaveRepository, TodoProperties todoProperties) {
        this.todoSaveRepository = todoSaveRepository;
        this.todoProperties = todoProperties;
    }


    public Todo newTodo(String description) {
        Todo.create(description, todoProperties.owner());
        return todoSaveRepository.save(Todo.create(description, todoProperties.owner()));
    }


}
