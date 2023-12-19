package com.cleveritgroup.utquickdemo.todo.application;

import com.cleveritgroup.utquickdemo.todo.config.TodoProperties;
import com.cleveritgroup.utquickdemo.todo.domain.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceMockitoTest {
    @Mock
    private TodoSaveRepository todoSaveRepository;

    @Mock
    private TodoProperties todoProperties ;

    @InjectMocks
    private TodoService todoService;

    @Test
    void shouldSaveTodo() {
        // Given
        String description = "my awesome todo";
        String owner = "Dummy-owner";
        Todo todo = Todo.create(description, owner);
        when(todoProperties.owner()).thenReturn(owner);
        when(todoSaveRepository.save(any())).thenReturn(todo);
        // When
        Todo savedTodo = todoService.newTodo(description);
        // Then
        assertNotNull(savedTodo);
        assertEquals(description, savedTodo.description());
        assertEquals(owner, savedTodo.owner());
        verify(todoSaveRepository, times(1)).save(any());
    }
    @Test
    void shouldSaveTodo2() {
        // Given
        String description = "my awesome todo 2";
        String owner = "Dummy-owner";
        Todo todo = Todo.create(description, owner);
        when(todoProperties.owner()).thenReturn(owner);
        when(todoSaveRepository.save(any())).thenReturn(todo);
        // When
        Todo savedTodo = todoService.newTodo(description);
        // Then
        assertNotNull(savedTodo);
        assertEquals(description, savedTodo.description());
        assertEquals(owner, savedTodo.owner());
        verify(todoSaveRepository, times(1)).save(any());
    }
}