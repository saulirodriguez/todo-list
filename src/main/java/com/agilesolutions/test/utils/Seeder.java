package com.agilesolutions.test.utils;

import com.agilesolutions.test.exception.ExceptionHandlingController;
import com.agilesolutions.test.entity.Task;
import com.agilesolutions.test.entity.ToDo;
import com.agilesolutions.test.repository.TaskRepository;
import com.agilesolutions.test.repository.ToDoRepository;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Seeder implements CommandLineRunner {
    private TaskRepository taskRepository;
    private ToDoRepository todoRepository;
    private static final Logger LOGGER = Logger.getLogger(ExceptionHandlingController.class);

    public Seeder(TaskRepository taskRepository, ToDoRepository todoRepository) {
        this.taskRepository = taskRepository;
        this.todoRepository = todoRepository;
    }

    @Override
    public void run(String ...args) {
        LOGGER.info("Running Seeder...");
        ToDo todoList = new ToDo();
        todoList.setName("My Todo List");
        todoList.setDescription("My Todo List Description");

        List<Task> players = new ArrayList<>();
        for(int i = 1; i <6; i++) {
            Task task = new Task();
            task.setName("Task " + i);
            task.setDescription("Task Description " + i);
            task.setOwner(todoList);
            players.add(task);
        }

        this.todoRepository.save(todoList);
        this.taskRepository.save(players);
        LOGGER.info("DONE");
    }
}
