package com.cleveritgroup.utquickdemo.todo.application;

import com.cleveritgroup.utquickdemo.todo.config.TodoProperties;
import com.cleveritgroup.utquickdemo.todo.domain.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>This class contains test cases for the TodoService class.</p>

 * <p>In these tests, we are using self-made fakes instead of using mocks created with libraries like Mockito.
 * The main reasons we prefer fakes over mocks are simplicity and performance:</p>
 *
 * <p>- Simplicity: Writing our own fakes makes the test easier to understand. It makes the code less complicated
 *   because it eliminates the complexity introduced by the mock library, which might have its own idiosyncrasies and limitations.</p>
 *
 * <p>- Performance: Fakes can be more performant than mocks as they do less under the hood. Mock libraries may involve
 *   reflection, proxies, and other underlying processes that could slow down the performance of the test.</p>
 */
class TodoServiceTest {

    private final TodoProperties todoProperties = new TodoProperties("Dummy-owner");
    private final List<Todo> todos = new ArrayList<>(1);
    private final TodoSaveRepository todoSaveRepository = todo -> {
        todos.add(todo);
        return todo;
    };
    private final TodoService todoService = new TodoService(todoSaveRepository, todoProperties);

    @Test
    void newTodoShouldBeSaved() {
        // Given
        String description = "my awesome todo";
        // When
        Todo savedTodo = todoService.newTodo(description);
        // Then
        assertNotNull(savedTodo);
        assertEquals(description, savedTodo.description());
        assertEquals(todoProperties.owner(), savedTodo.owner());
        assertEquals(1, todos.size());
        assertEquals(savedTodo, todos.get(0));

    }
    @Test
    void newTodoShouldBeSaved2() {
        // Given
        String description = "my awesome todo 2";
        // When
        Todo savedTodo = todoService.newTodo(description);
        // Then
        assertNotNull(savedTodo);
        assertEquals(description, savedTodo.description());
        assertEquals(todoProperties.owner(), savedTodo.owner());
        assertEquals(1, todos.size());
        assertEquals(savedTodo, todos.get(0));
    }
}