package com.cleveritgroup.utquickdemo.todo.repository;

import com.cleveritgroup.utquickdemo.todo.application.TodoSaveRepository;
import com.cleveritgroup.utquickdemo.todo.domain.Todo;
import org.springframework.stereotype.Repository;

@Repository
class TodoMongoRepository implements TodoSaveRepository {
    private final TodoDao dao;

    public TodoMongoRepository(TodoDao dao) {
        this.dao = dao;
    }

    @Override
    public Todo save(Todo todo) {
        return dao.save(TodoDto.fromDomain(todo)).toDomain();
    }

}
