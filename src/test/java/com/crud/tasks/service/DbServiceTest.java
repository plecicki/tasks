package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class DbServiceTest {

    @Autowired
    private DbService dbService;

    @Test
    void getTaskTest() {
        //Given
        Task task = new Task();
        task.setTitle("Task");
        task.setContent("Task content");
        dbService.saveTask(task);
        Task taskFromDb = new Task();

        //When
        try {
            taskFromDb = dbService.getTask(task.getId());
        } catch (TaskNotFoundException e) {

        }

        //Then
        Assertions.assertEquals("Task", taskFromDb.getTitle());
        Assertions.assertEquals("Task content", taskFromDb.getContent());

        //CleanUp
        dbService.deleteTask(task.getId());
    }

    @Test
    void getTasksTest() {
        //Given
        Task task1 = new Task();
        task1.setTitle("Task1");
        task1.setContent("Task content1");
        dbService.saveTask(task1);
        Task task2 = new Task();
        task2.setTitle("Task2");
        task2.setContent("Task content2");
        dbService.saveTask(task2);
        Task task3 = new Task();
        task3.setTitle("Task3");
        task3.setContent("Task content3");
        dbService.saveTask(task3);

        //When
        List<Task> tasks = dbService.getAllTasks();

        //Then
        Assertions.assertEquals("Task1", tasks.get(tasks.size()-3).getTitle());
        Assertions.assertEquals("Task2", tasks.get(tasks.size()-2).getTitle());
        Assertions.assertEquals("Task3", tasks.get(tasks.size()-1).getTitle());
        Assertions.assertEquals("Task content1", tasks.get(tasks.size()-3).getContent());
        Assertions.assertEquals("Task content2", tasks.get(tasks.size()-2).getContent());
        Assertions.assertEquals("Task content3", tasks.get(tasks.size()-1).getContent());

        //CleanUp
        dbService.deleteTask(task1.getId());
        dbService.deleteTask(task2.getId());
        dbService.deleteTask(task3.getId());
    }

    @Test
    void saveTaskTest() {
        //Given
        Task task = new Task();
        task.setTitle("Task");
        task.setContent("Task content");
        //When
        dbService.saveTask(task);
        //Then
        Assertions.assertNotNull(task.getId());
        //CleanUp
        dbService.deleteTask(task.getId());
    }

    @Test
    void deleteTaskTest() {
        //Given
        Task task = new Task();
        task.setTitle("Task");
        task.setContent("Task content");
        dbService.saveTask(task);
        //When
        dbService.deleteTask(task.getId());
        //Then
        boolean taskNotFound = false;
        Task taskAfterDelete = new Task();
        try {
            taskAfterDelete = dbService.getTask(task.getId());
        } catch (TaskNotFoundException e) {
            taskNotFound = true;
        }
        Assertions.assertNull(taskAfterDelete.getId());
        Assertions.assertTrue(taskNotFound);
    }
}
