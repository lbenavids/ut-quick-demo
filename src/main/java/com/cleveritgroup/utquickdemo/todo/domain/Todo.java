package com.cleveritgroup.utquickdemo.todo.domain;

import java.time.Clock;
import java.time.LocalDateTime;

public record Todo(String id, String description, String owner, Status status, LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
    static Clock clock = Clock.systemDefaultZone();

    public static Todo create(String description, String owner) {
        return new Todo(
                null,
                description,
                owner,
                Status.PENDING,
                LocalDateTime.now(clock),
                LocalDateTime.now(clock)
        );
    }

    public Todo updateStatus() {
        Status newStatus = switch (status) {
            case PENDING -> Status.WORKING;
            case WORKING, COMPLETED -> Status.COMPLETED;
        };

        if (newStatus == status) {
            return this;
        }

        return new Todo(
                id,
                description,
                owner,
                newStatus,
                createdAt,
                LocalDateTime.now(clock)
        );
    }


}
