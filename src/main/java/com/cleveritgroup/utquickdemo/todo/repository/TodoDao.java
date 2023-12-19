package com.cleveritgroup.utquickdemo.todo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

interface TodoDao extends MongoRepository<TodoDto, String> {
}
