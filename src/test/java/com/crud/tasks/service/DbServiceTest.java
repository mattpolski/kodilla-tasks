package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {
    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void shouldGetAllTasks() {
        Task task1 = new Task(1L, "Task1", "Task for test");
        Task task2 = new Task(2L, "Task2", "Another task for test");

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> allTasks = taskRepository.findAll();

        Assert.assertEquals(2, allTasks.size());
    }

    @Test
    public void shouldGetEmptyList() {
        when(taskRepository.findAll()).thenReturn(new ArrayList<>());

        List<Task> emptyList = dbService.getAllTasks();

        Assert.assertEquals(0, emptyList.size());
    }

    @Test
    public void shouldSaveTask() {
        Task task1 = new Task(1L, "Task1", "Task for test");
        when(taskRepository.save(task1)).thenReturn(task1);

        Task test = dbService.saveTask(task1);

        Assert.assertEquals(task1.getTitle(), test.getTitle());
        Assert.assertEquals(task1.getContent(), test.getContent());
        Assert.assertEquals(task1.getId(), test.getId());

    }
}
