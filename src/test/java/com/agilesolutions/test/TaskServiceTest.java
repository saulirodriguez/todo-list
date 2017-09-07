package com.agilesolutions.test;

import com.agilesolutions.test.exception.BadRequestException;
import com.agilesolutions.test.exception.ResourceNotFoundException;
import com.agilesolutions.test.model.Task;
import com.agilesolutions.test.model.ToDo;
import com.agilesolutions.test.repository.TaskRepository;
import com.agilesolutions.test.repository.ToDoRepository;
import com.agilesolutions.test.service.TaskService;

import com.agilesolutions.test.service.ToDoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(SpringRunner.class)
public class TaskServiceTest {
    // Mock Data
    private final List<Task> tasks = new ArrayList<>();
    private final Task newTask = new Task();
    private final ToDo todoList = new ToDo((long)1, "TODO", "TODO");

    @TestConfiguration
    static class TaskServiceTestContextConfiguration {

        @Bean
        public TaskService taskService() {
            return new TaskService();
        }
        @Bean
        public ToDoService todoService() {
            return new ToDoService();
        }
    }

    @Autowired
    private TaskService taskService;
    @Autowired
    private ToDoService todoService;

    @MockBean
    private TaskRepository taskRepository;
    @MockBean
    private ToDoRepository todoRepository;

    @Before
    public void setUp() {
        for(int i = 1; i <6; i++) {
            Task t = new Task();
            t.setId((long) i);
            t.setName(String.format("Task %s", i));
            t.setOwner(todoList);
            tasks.add(t);
        }

        newTask.setId((long) 6);
        newTask.setName("New Task");
        newTask.setOwner(todoList);

        Mockito.when(taskRepository.findByOwnerId(todoList.getId()))
                .thenReturn(tasks);

        Mockito.when(taskRepository.findOne(tasks.get(0).getId()))
                .thenReturn(tasks.get(0));

        Mockito.when(taskRepository.save(newTask))
                .thenReturn(newTask);

        Mockito.when(taskRepository.save(tasks.get(0)))
                .thenReturn(tasks.get(0));

        Mockito.when(todoRepository.findOne(todoList.getId()))
                .thenReturn(todoList);
    }

    @Test
    public void findAllShouldGetAllTasksByToDoList() {
        List<Task> found = taskService.findAll(todoList.getId());

        assertThat(found.size())
                .isEqualTo(5);
    }

    @Test
    public void findAllShouldGetEmptyArrayWhenNoToDoListFound() {
        List<Task> found = taskService.findAll((long) 2);

        assertThat(found.size())
                .isEqualTo(0);
    }

    @Test
    public void findByIdShouldGetSelectedTask() throws ResourceNotFoundException {
        Task found = taskService.findById((long) 1);

        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(1);
        assertThat(found.getName()).isEqualTo("Task 1");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findByIdShouldThrowsResourceNotFound() throws ResourceNotFoundException {
        taskService.findById((long) 10);
    }

    @Test
    public void saveShouldReturnSavedTask() throws BadRequestException, ResourceNotFoundException {
        Task found = taskService.save(newTask, todoList.getId());

        assertThat(found).isNotNull();
        assertThat(found.getId()).isNotNull().isEqualTo(6);
        assertThat(found.getName()).isNotNull().isEqualTo("New Task");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void saveShouldThrowsNotFoundExceptionWhenNoToDoListFound() throws BadRequestException, ResourceNotFoundException {
        taskService.save(newTask, (long) 10);
    }

    @Test(expected = BadRequestException.class)
    public void saveShouldThrowsBadRequestExceptionWhenNoName() throws BadRequestException, ResourceNotFoundException {
        Task invalidTask = new Task();
        invalidTask.setDescription("Name is a required field");

        taskService.save(invalidTask, (long) 1);
    }

    @Test
    public void updateShouldReturnUpdatedTask() throws ResourceNotFoundException {
        Task newValues = new Task();
        newValues.setName("Updated Task");
        newValues.setDescription("Updated");
        Task updated = taskService.update((long) 1, newValues, todoList.getId());

        assertThat(updated).isNotNull();
        assertThat(updated.getId()).isNotNull().isEqualTo(1);
        assertThat(updated.getName()).isNotNull().isEqualTo(newValues.getName());
        assertThat(updated.getDescription()).isNotNull().isEqualTo(newValues.getDescription());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateShouldThrowsNotFoundExceptionWhenNoName() throws ResourceNotFoundException {
        Task newValues = new Task();
        newValues.setName("Name");
        newValues.setDescription("Description");

        taskService.update((long) 10, newValues, todoList.getId());
    }

    @Test
    public void deleteShouldCallRepositoryDelete() throws ResourceNotFoundException {
        taskService.delete((long) 1);

        verify(taskRepository, times(1)).delete(tasks.get(0));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteShouldThrowsNotFoundException() throws ResourceNotFoundException {
        taskService.delete((long) 10);
    }
}
