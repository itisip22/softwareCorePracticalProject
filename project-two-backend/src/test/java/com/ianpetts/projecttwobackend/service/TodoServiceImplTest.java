package com.ianpetts.projecttwobackend.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ianpetts.projecttwobackend.model.Todo;
import com.ianpetts.projecttwobackend.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TodoServiceImpl.class})
@ExtendWith(SpringExtension.class)
class TodoServiceImplTest {
    @MockBean
    private TodoRepository todoRepository;

    @Autowired
    private TodoServiceImpl todoServiceImpl;

    @Test
    void testSaveTodo() {
        Todo todo = new Todo();
        todo.setDescription("Test Save Todo 1");
        todo.setId(1);
        todo.setTitle("Test 1");
        when(todoRepository.save((Todo) any())).thenReturn(todo);

        Todo todo1 = new Todo();
        todo1.setDescription("Test Save Todo 1.1");
        todo1.setId(1);
        todo1.setTitle("Test 1.1");
        assertSame(todo, todoServiceImpl.saveTodo(todo1));
        verify(todoRepository).save((Todo) any());
    }

    @Test
    void testSaveTodo2() {
        when(todoRepository.save((Todo) any())).thenThrow(new IllegalStateException("Unable to Save Todo"));

        Todo todo = new Todo();
        todo.setDescription("Test Save Todo 2");
        todo.setId(1);
        todo.setTitle("Test 3");
        assertThrows(IllegalStateException.class, () -> todoServiceImpl.saveTodo(todo));
        verify(todoRepository).save((Todo) any());
    }

    @Test
    void testGetAllTodos() {
        ArrayList<Todo> todoList = new ArrayList<>();
        when(todoRepository.findAll()).thenReturn(todoList);
        List<Todo> actualAllTodos = todoServiceImpl.getAllTodos();
        assertSame(todoList, actualAllTodos);
        assertTrue(actualAllTodos.isEmpty());
        verify(todoRepository).findAll();
    }


    @Test
    void testGetAllTodos2() {
        when(todoRepository.findAll()).thenThrow(new IllegalStateException("Unable to get all Todos"));
        assertThrows(IllegalStateException.class, () -> todoServiceImpl.getAllTodos());
        verify(todoRepository).findAll();
    }

    @Test
    void testUpdateTodo() {
        Todo todo = new Todo();
        todo.setDescription("Test Update Todo 1");
        todo.setId(1);
        todo.setTitle("Test Update 1");
        Optional<Todo> ofResult = Optional.of(todo);
        when(todoRepository.findById((Integer) any())).thenReturn(ofResult);
        todoServiceImpl.updateTodo(1, "Test 1", "Test Update Todo 1");
        verify(todoRepository).findById((Integer) any());
    }

    @Test
    void testDeleteTodo() {
        doNothing().when(todoRepository).deleteById((Integer) any());
        when(todoRepository.existsById((Integer) any())).thenReturn(true);
        todoServiceImpl.deleteTodo(1);
        verify(todoRepository).existsById((Integer) any());
        verify(todoRepository).deleteById((Integer) any());
    }


    @Test
    void testDeleteTodo2() {
        doThrow(new IllegalStateException("Unable to Delete Todo")).when(todoRepository).deleteById((Integer) any());
        when(todoRepository.existsById((Integer) any())).thenReturn(true);
        assertThrows(IllegalStateException.class, () -> todoServiceImpl.deleteTodo(1));
        verify(todoRepository).existsById((Integer) any());
        verify(todoRepository).deleteById((Integer) any());
    }

    @Test
    void testDeleteTodo3() {
        doNothing().when(todoRepository).deleteById((Integer) any());
        when(todoRepository.existsById((Integer) any())).thenReturn(false);
        assertThrows(IllegalStateException.class, () -> todoServiceImpl.deleteTodo(1));
        verify(todoRepository).existsById((Integer) any());
    }
}

