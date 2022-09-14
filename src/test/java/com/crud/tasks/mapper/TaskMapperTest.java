package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Task", "Task content");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        Assertions.assertEquals(1L, task.getId());
        Assertions.assertEquals("Task", task.getTitle());
        Assertions.assertEquals("Task content", task.getContent());
    }

    @Test
    void mapToTaskDtoTest() {
        //Given
        Task task = new Task(1L, "Task", "Task content");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        Assertions.assertEquals(1L, taskDto.getId());
        Assertions.assertEquals("Task", taskDto.getTitle());
        Assertions.assertEquals("Task content", taskDto.getContent());
    }

    @Test
    void mapToTaskDtoListTest() {
        //Given
        Task task1 = new Task(1L, "Task1", "Task content1");
        Task task2 = new Task(2L, "Task2", "Task content2");
        Task task3 = new Task(3L, "Task3", "Task content3");
        List<Task> tasks = Arrays.asList(task1,task2,task3);
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);
        //Then
        Assertions.assertEquals(1L, taskDtos.get(0).getId());
        Assertions.assertEquals(2L, taskDtos.get(1).getId());
        Assertions.assertEquals(3L, taskDtos.get(2).getId());
        Assertions.assertEquals("Task1", taskDtos.get(0).getTitle());
        Assertions.assertEquals("Task2", taskDtos.get(1).getTitle());
        Assertions.assertEquals("Task3", taskDtos.get(2).getTitle());
        Assertions.assertEquals("Task content1", taskDtos.get(0).getContent());
        Assertions.assertEquals("Task content2", taskDtos.get(1).getContent());
        Assertions.assertEquals("Task content3", taskDtos.get(2).getContent());
    }
}
