package com.ianpetts.projecttwobackend.service;

import com.ianpetts.projecttwobackend.model.Todo;

import java.util.List;

public interface TodoService {

    public Todo saveTodo(Todo todo);

    public List<Todo> getAllTodos();

    public void updateTodo(Integer id, String title, String description);

    public void deleteTodo(Integer id);
}
