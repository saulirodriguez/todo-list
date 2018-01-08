package com.agilesolutions.test;

import com.agilesolutions.test.exception.BadRequestException;
import com.agilesolutions.test.exception.ResourceNotFoundException;
import com.agilesolutions.test.entity.ToDo;
import com.agilesolutions.test.repository.ToDoRepository;
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
public class ToDoServiceTest {
    // Mock Data
    private final List<ToDo> todos = new ArrayList<>();
    private final ToDo newToDo = new ToDo();

    @TestConfiguration
    static class ToDoServiceTestContextConfiguration {

        @Bean
        public ToDoService todoService() {
            return new ToDoService();
        }
    }

    @Autowired
    private ToDoService todoService;

    @MockBean
    private ToDoRepository todoRepository;

    @Before
    public void setUp() {
        for(int i = 1; i <6; i++) {
            ToDo p = new ToDo();
            p.setId((long) i);
            p.setName(String.format("ToDo %s", i));
            todos.add(p);
        }

        newToDo.setId((long) 6);
        newToDo.setName("New ToDo");

        Mockito.when(todoRepository.findAll())
                .thenReturn(todos);

        Mockito.when(todoRepository.findOne(todos.get(0).getId()))
                .thenReturn(todos.get(0));

        Mockito.when(todoRepository.save(newToDo))
                .thenReturn(newToDo);

        Mockito.when(todoRepository.save(todos.get(0)))
                .thenReturn(todos.get(0));
    }

    @Test
    public void findAllShouldGetAllToDos() {
        List<ToDo> found = todoService.findAll();

        assertThat(found.size())
                .isEqualTo(5);
    }

    @Test
    public void findByIdShouldGetSelectedToDo() throws ResourceNotFoundException {
        ToDo found = todoService.findById((long) 1);

        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(1);
        assertThat(found.getName()).isEqualTo("ToDo 1");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findByIdShouldThrowsResourceNotFound() throws ResourceNotFoundException {
        todoService.findById((long) 10);
    }

    @Test
    public void saveShouldReturnSavedToDo() throws BadRequestException, Exception {
        ToDo found = todoService.save(newToDo);

        assertThat(found).isNotNull();
        assertThat(found.getId()).isNotNull().isEqualTo(6);
        assertThat(found.getName()).isNotNull().isEqualTo("New ToDo");
    }

    @Test(expected = BadRequestException.class)
    public void saveShouldThrowsBadRequestExceptionWhenNoName() throws BadRequestException, Exception {
        ToDo invalidToDo = new ToDo();
        invalidToDo.setDescription("Name is a required field");

        todoService.save(invalidToDo);
    }

    @Test
    public void updateShouldReturnUpdatedToDo() throws ResourceNotFoundException, Exception  {
        ToDo newValues = new ToDo();
        newValues.setName("Updated ToDo");
        newValues.setDescription("Updated");
        ToDo updated = todoService.update((long) 1, newValues);

        assertThat(updated).isNotNull();
        assertThat(updated.getId()).isNotNull().isEqualTo(1);
        assertThat(updated.getName()).isNotNull().isEqualTo(newValues.getName());
        assertThat(updated.getDescription()).isNotNull().isEqualTo(newValues.getDescription());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateShouldThrowsNotFoundExceptionWhenNoName() throws ResourceNotFoundException, Exception  {
        ToDo newValues = new ToDo();
        newValues.setName("Name");
        newValues.setDescription("Description");

        todoService.update((long) 10, newValues);
    }

    @Test
    public void deleteShouldCallRepositoryDelete() throws ResourceNotFoundException, Exception  {
        todoService.delete((long) 1);

        verify(todoRepository, times(1)).delete(todos.get(0));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteShouldThrowsNotFoundException() throws ResourceNotFoundException, Exception  {
        todoService.delete((long) 10);
    }
}
