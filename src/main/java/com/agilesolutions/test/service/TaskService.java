package com.agilesolutions.test.service;

import com.agilesolutions.test.exception.BadRequestException;
import com.agilesolutions.test.exception.ResourceNotFoundException;
import com.agilesolutions.test.entity.Task;
import com.agilesolutions.test.entity.ToDo;
import com.agilesolutions.test.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ToDoService todoService;

    @Transactional(readOnly = true)
    public List<Task> findByOwnerId(Long todoId) {
        return this.taskRepository.findByOwnerId(todoId);
    }

    @Transactional(readOnly = true)
    public List<Task> find(Long todoId, Optional<String> optionalName, Optional<String> optionalDesc) {
        List<Task> taskList = this.taskRepository.findByOwnerId(todoId);

        if(optionalName.isPresent()) {
            String name = optionalName.get().toLowerCase();
            taskList = taskList.stream()
                    .filter(task -> task.getName().toLowerCase().contains(name))
                    .collect(Collectors.toList());
        }

        if(optionalDesc.isPresent()) {
            String description = optionalDesc.get().toLowerCase();
            taskList = taskList.stream()
                    .filter(task -> task.getDescription().toLowerCase().contains(description))
                    .collect(Collectors.toList());
        }

        return taskList;
    }

    @Transactional(readOnly = true)
    public Task findById(Long id) throws ResourceNotFoundException {
        Task task = this.taskRepository.findOne(id);
        if(task == null) {
            throw new ResourceNotFoundException("Task", id);
        }

        return task;
    }

    @Transactional
    public Task save(Task task, Long todoId) throws BadRequestException, ResourceNotFoundException {
        if (task.getName() == null || task.getName().isEmpty()) {
            throw new BadRequestException("Task List", "Required Field: name");
        }

        ToDo todo = this.todoService.findById(todoId);
        task.setOwner(todo);

        return this.taskRepository.save(task);
    }

    @Transactional
    public Task update(Long id, Task updatedTask, Long todoId) throws ResourceNotFoundException {
        Task existentTask = this.findById(id);
        existentTask.setName(updatedTask.getName());
        existentTask.setDescription(updatedTask.getDescription());

        ToDo todo = this.todoService.findById(todoId);
        existentTask.setOwner(todo);

        return this.taskRepository.save(existentTask);
    }

    @Transactional
    public Task delete(Long id) throws ResourceNotFoundException {
        Task deletedTask = this.findById(id);
        this.taskRepository.delete(deletedTask);
        return deletedTask;
    }
}
