package com.cleveritgroup.utquickdemo.todo.application;

import com.cleveritgroup.utquickdemo.todo.domain.Todo;

@FunctionalInterface
public interface TodoSaveRepository {

    Todo save(Todo todo);

}
