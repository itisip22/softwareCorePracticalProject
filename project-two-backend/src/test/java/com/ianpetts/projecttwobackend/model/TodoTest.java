package com.ianpetts.projecttwobackend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TodoTest {
    @Test
    void testConstructor() {
        Todo actualTodo = new Todo();
        actualTodo.setDescription("Constructor Test 1");
        actualTodo.setId(1);
        actualTodo.setTitle("Test 1");
        String actualToStringResult = actualTodo.toString();
        assertEquals("Constructor Test 1", actualTodo.getDescription());
        assertEquals(1, actualTodo.getId().intValue());
        assertEquals("Test 1", actualTodo.getTitle());
        assertEquals("Todo{id=1, title='Test 1', description='Constructor Test 1'}",
                actualToStringResult);
    }

    @Test
    void testConstructor2() {
        Todo actualTodo = new Todo(1, "Test 2", "Constructor Test 2");
        actualTodo.setDescription("Constructor Test 2.1");
        actualTodo.setId(1);
        actualTodo.setTitle("Test 2.1");
        String actualToStringResult = actualTodo.toString();
        assertEquals("Constructor Test 2.1", actualTodo.getDescription());
        assertEquals(1, actualTodo.getId().intValue());
        assertEquals("Test 2.1", actualTodo.getTitle());
        assertEquals("Todo{id=1, title='Test 2.1', description='Constructor Test 2.1'}",
                actualToStringResult);
    }
}

