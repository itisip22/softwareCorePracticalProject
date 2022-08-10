package com.ianpetts.projecttwobackend.controller;

import com.ianpetts.projecttwobackend.model.Todo;
import com.ianpetts.projecttwobackend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@CrossOrigin
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping()
    public List<Todo> getAllTodos(){
        return todoService.getAllTodos();
    }

    @PostMapping("/save")
    public String save(@RequestBody Todo todo){
        todoService.saveTodo(todo);
        return "New Todo saved to DB!";
    }

    @PutMapping("{id}")
    public void updateTodo(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description) {
        todoService.updateTodo(id, title, description);
    }

    @DeleteMapping(path = "{id}")
    public void deleteTodo(@PathVariable("id") Integer id){
        todoService.deleteTodo(id);
    }
}
