package com.cleveritgroup.utquickdemo.todo.repository;

import com.cleveritgroup.utquickdemo.todo.domain.Status;
import com.cleveritgroup.utquickdemo.todo.domain.Todo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("todos")
record TodoDto(@Id String id, String description, String owner, Status status, LocalDateTime createdAt,
               LocalDateTime updatedAt) {


    static TodoDto fromDomain(Todo todo) {
        return new TodoDto(
                todo.id(),
                todo.description(),
                todo.owner(),
                todo.status(),
                todo.createdAt(),
                todo.updatedAt()
        );
    }

    Todo toDomain() {
        return new Todo(
                id,
                description,
                owner,
                status,
                createdAt,
                updatedAt
        );
    }

}
