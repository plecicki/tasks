package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    void shouldFetchListWithTasks() throws Exception {
        //Given
        List<Task> tasks = Arrays.asList(
                new Task(1L, "Task1", "TaskContent1"),
                new Task(2L, "Task2", "TaskContent2"),
                new Task(3L, "Task3", "TaskContent3")
        );
        List<TaskDto> taskDtos = Arrays.asList(
                new TaskDto(1L, "Task1", "TaskContent1"),
                new TaskDto(2L, "Task2", "TaskContent2"),
                new TaskDto(3L, "Task3", "TaskContent3")
        );
        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("Task1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("TaskContent1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title", Matchers.is("Task2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].content", Matchers.is("TaskContent2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].title", Matchers.is("Task3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].content", Matchers.is("TaskContent3")));
    }

    @Test
    void shouldFetchTask() throws Exception {
        //Given
        Task task = new Task(1L, "Task", "TaskContent");
        TaskDto taskDto = new TaskDto(1L, "Task", "TaskContent");
        when(dbService.getTask(1L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("TaskContent")));
    }

    @Test
    void shouldCreateTask() throws Exception {
        //Given
        Task task = new Task(1L, "Task", "TaskContent");
        TaskDto taskDto = new TaskDto(1L, "Task", "TaskContent");

        when(dbService.saveTask(any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateTask() throws Exception {
        //Given
        Task task1 = new Task(1L, "Task", "TaskContent");
        Task task2 = new Task(1L, "Task", "TaskContent");
        TaskDto taskDto1 = new TaskDto(1L, "Task", "TaskContent");
        TaskDto taskDto2 = new TaskDto(1L, "Task", "TaskContent");

        when(taskMapper.mapToTask(taskDto1)).thenReturn(task1);
        when(dbService.saveTask(task1)).thenReturn(task2);
        when(taskMapper.mapToTaskDto(task2)).thenReturn(taskDto2);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto2);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldDeleteTask() throws Exception {
        //Given & When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
