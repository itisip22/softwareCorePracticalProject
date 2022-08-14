package com.ianpetts.projecttwobackend.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ianpetts.projecttwobackend.model.Todo;
import com.ianpetts.projecttwobackend.service.TodoService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TodoController.class})
@ExtendWith(SpringExtension.class)
class TodoControllerTest {
    @Autowired
    private TodoController todoController;

    @MockBean
    private TodoService todoService;

    @Test
    void testGetAllTodos() throws Exception {
        when(todoService.getAllTodos()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/todos");
        MockMvcBuilders.standaloneSetup(todoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllTodos2() throws Exception {
        when(todoService.getAllTodos()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/todos");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(todoController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testUpdateTodo() throws Exception {
        doNothing().when(todoService).updateTodo((Integer) any(), (String) any(), (String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/todos/{id}", 1);
        MockMvcBuilders.standaloneSetup(todoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testUpdateTodo2() throws Exception {
        doNothing().when(todoService).updateTodo((Integer) any(), (String) any(), (String) any());
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/api/v1/todos/{id}", 1);
        putResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(todoController)
                .build()
                .perform(putResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteTodo() throws Exception {
        doNothing().when(todoService).deleteTodo((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/todos/{id}", 1);
        MockMvcBuilders.standaloneSetup(todoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteTodo2() throws Exception {
        doNothing().when(todoService).deleteTodo((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v1/todos/{id}", 1);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(todoController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testSave() throws Exception {
        Todo todo = new Todo();
        todo.setDescription("The characteristics of someone or something");
        todo.setId(1);
        todo.setTitle("Dr");
        when(todoService.saveTodo((Todo) any())).thenReturn(todo);

        Todo todo1 = new Todo();
        todo1.setDescription("The characteristics of someone or something");
        todo1.setId(1);
        todo1.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(todo1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/todos/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(todoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("New Todo saved to DB!"));
    }
}

