package com.ianpetts.projecttwobackend.service;

import com.ianpetts.projecttwobackend.model.Todo;
import com.ianpetts.projecttwobackend.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;


    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    @Transactional
    public void updateTodo(Integer id, String title, String description) {

        Optional<Todo> optionalTodo = todoRepository.findById(id);
        Todo todoUpdate = optionalTodo
                .orElseThrow(() -> new IllegalStateException("Todo with id " + id + " does not exist!"));


        if(title != null && title.length() > 0 && !Objects.equals(todoUpdate.getTitle(), title)){
            todoUpdate.setTitle(title);
        }

        if(description != null && description.length() > 0 && !Objects.equals(todoUpdate.getDescription(), description)){
            todoUpdate.setDescription(description);
        }
    }


    @Override
    public void deleteTodo(Integer id ){
        boolean exists = todoRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Todo with ID " + id + " does not exist!");
        }
        todoRepository.deleteById(id);
    }
}
