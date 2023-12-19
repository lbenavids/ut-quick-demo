package com.cleveritgroup.utquickdemo.todo.domain;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>The {@code TodoTest} class is a JUnit test class that contains test cases for the {@link Todo} class.</p>
 * <p>It verifies the behavior of the {@link Todo} class by testing its methods.</p>
 * <p>We are using Clock here to have a consistent time reference in our test. Without it,
 * there might be few milliseconds difference between the time when the expectedLocalDateTime was set and the
 * time when todo.createdAt() and todo.updatedAt() methods were called.
 * This would make the assertion to fail. By using the Clock to fix time, we avoid this timing issue
 * and make our test more reliable.</p>
 *
*/

class TodoTest {

    @Test
    void newTodoWithDescription() {
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2021, 9, 1, 0, 0, 0, 0);
        setHourToTest(expectedLocalDateTime);

        Todo todo = Todo.create("description", "owner");

        assertEquals("description", todo.description());
        assertEquals("owner", todo.owner());
        assertEquals(Status.PENDING, todo.status());
        assertEquals(expectedLocalDateTime, todo.createdAt());
        assertEquals(expectedLocalDateTime, todo.updatedAt());
    }

    private void setHourToTest(LocalDateTime expectedLocalDateTime) {

        ZoneId zone = ZoneId.of("America/Santiago");

        ZonedDateTime now = ZonedDateTime.of(expectedLocalDateTime, zone);
        Todo.clock= Clock.fixed(now.toInstant(), zone);
    }
}