package com.cleveritgroup.utquickdemo.todo.repository;

import com.cleveritgroup.utquickdemo.todo.domain.Todo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * <p>This class contains unit tests for the TodoMongoRepository class.</p>
 *
 * <p>We are using Testcontainers for our database testing needs where a Docker container
 * runs a MongoDB instance. The main advantage of using Testcontainers is that it ensures a consistent
 * environment between developers, CI servers and wherever else the tests are run.</p>
 *
 * <p>It also allows our tests to be infrastructure agnostic and simplifies control and configuration
 * of the environment that our tests run in.</p>
 *
 * <p>The purpose of Testcontainers in this context is to provide a real MongoDB instance for our test.
 * This approach allows us to avoid mocking the database interaction and makes the test more reliable
 * by covering database interaction logic.</p>
 *
 * <p>This matters especially when testing against a database because it eliminates the discrepancies
 * between the behavior of the mocked database and the real one.</p>
 *
 * <p>The cost of starting up a separate database for each developer or test run might seem high,
 * but the improved reliability of tests can save a lot of effort in debugging subtle differences
 * or understanding test failures.</p>
 *
 * <p>Testcontainers make this cost acceptable by automating the setup and management of containers for tests.</p>
 */
@DataMongoTest
@Testcontainers
public class TodoMongoRepositoryTest {

    @Container
    public static MongoDBContainer container = new MongoDBContainer("mongo:5.0");

    @DynamicPropertySource
    public static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", container::getReplicaSetUrl);
    }

    @Autowired
    TodoDao dao;

    @BeforeEach
    void setUp() {
        dao.deleteAll();
    }

    @Nested
    class SaveTodoTest {
        private final TodoMongoRepository repo = new TodoMongoRepository(dao);
        private final Todo newTodo = Todo.create("Description", "Owner");
        private Todo actualTodo;
        private Todo expected;

        @BeforeEach
        void setup() {
            actualTodo = repo.save(newTodo);
            TodoDto stored = dao.findOne(Example.of(TodoDto.fromDomain(newTodo))).orElseThrow();
            expected = stored.toDomain();
        }

        @Test
        @DisplayName("Test ID is saved correctly")
        void idIsSavedCorrectly() {
            Assertions.assertEquals(expected.id(), actualTodo.id());
        }

        @Test
        @DisplayName("Test status is saved correctly")
        void statusIsSavedCorrectly() {
            Assertions.assertEquals(expected.status(), actualTodo.status());
        }

        @Test
        @DisplayName("Test description is saved correctly")
        void descriptionIsSavedCorrectly() {
            Assertions.assertEquals(expected.description(), actualTodo.description());
        }

        @Test
        @DisplayName("Test owner is saved correctly")
        void ownerIsSavedCorrectly() {
            Assertions.assertEquals(expected.owner(), actualTodo.owner());
        }
    }
}