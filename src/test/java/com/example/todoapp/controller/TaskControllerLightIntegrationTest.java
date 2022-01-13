package com.example.todoapp.controller;

import com.example.todoapp.model.Task;
import com.example.todoapp.model.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
public class TaskControllerLightIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskRepository repo;

    @Test
    void httpGet_returnsGivenTasks() throws Exception {
        //given
        String description = "foo";
        when(repo.findById(anyInt())).thenReturn(Optional.of(new Task("foo", LocalDateTime.now())));

        //when + then
        mockMvc.perform(get("/tasks/123"))
                .andDo(print())
                .andExpect(content().string(containsString(description)));
    }
}
