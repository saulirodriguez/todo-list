package com.agilesolutions.test.service;

import com.agilesolutions.test.exception.ResourceNotFoundException;
import com.agilesolutions.test.model.Task;
import com.agilesolutions.test.model.ToDo;
import com.agilesolutions.test.repository.TaskRepository;
import com.agilesolutions.test.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
//    @Autowired
//    private TaskRepository taskRepository;
//    @Autowired
//    private ToDoService todoService;
//
//    public List<Task> findAll(Long todoId, Optional<String> optionalName, Optional<String> optionalDesc) throws ResourceNotFoundException {
//        ToDo todo = this.todoService.findById(todoId);
//        List<Task> taskList = todo.getTasks();
//
//        if(optionalName.isPresent()) {
//            String name = optionalName.get().toLowerCase();
//
//            taskList = taskList.stream()
//                    .filter(task -> task.getName().toLowerCase().contains(name))
//                    .collect(Collectors.toList());
//        }
//
//        if(optionalDesc.isPresent()) {
//            String description = optionalDesc.get().toLowerCase();
//            taskList = taskList.stream()
//                    .filter(task -> task.getDescription().toLowerCase().contains(description))
//                    .collect(Collectors.toList());
//        }
//
//        return taskList;
//    }
//
//    public Task findById(Long ownerId, Long id) throws ResourceNotFoundException {
//        Task task = this.taskRepository.findById(ownerId, id);
//        if(task == null) {
//            throw new ResourceNotFoundException("Task", id);
//        }
//
//        return task;
//    }
//
//    public Task save(Task task) {
//        if(task.getDescription() == null) {
//            task.setDescription("");
//        }
//
//        this.taskRepository.save(task);
//        return task;
//    }
//
//    public Task update(Long ownerId, Long id, Task task) throws ResourceNotFoundException {
//        Task t = this.findById(ownerId, id);
//
//        if(task.getName() != null && !task.getName().isEmpty()) {
//            t.setName(task.getName());
//        }
//
//        if(task.getDescription() != null && !task.getDescription().isEmpty()) {
//            t.setDescription(task.getDescription());
//        }
//
//        if(task.getDateTime() != null) {
//            t.setDescription(task.getDescription());
//        }
//
//        this.taskRepository.save(t);
//        return t;
//    }
//
//    public void delete(Long id) {
//        this.taskRepository.delete(id);
//    }
}
