package com.agilesolutions.test.controller;

import com.agilesolutions.test.exception.BadRequestException;
import com.agilesolutions.test.exception.ResourceNotFoundException;
import com.agilesolutions.test.entity.Task;
import com.agilesolutions.test.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo-list/{todoId}/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> get(@PathVariable Long todoId, @RequestParam(required = false) String name, @RequestParam(required = false) String description) {
        if(name != null || description != null) {
            return ResponseEntity
                    .ok(this.taskService.find(todoId, Optional.ofNullable(name), Optional.ofNullable(description)));
        }

        return ResponseEntity
                    .ok(this.taskService.findByOwnerId(todoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) throws ResourceNotFoundException {
        Task task = this.taskService.findById(id);

        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task, @PathVariable Long todoId) throws BadRequestException, ResourceNotFoundException {
        if (task.getName() == null || task.getName().isEmpty()) {
            throw new BadRequestException("Task", "Required Field: name");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(this.taskService.save(task, todoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@RequestBody Task task, @PathVariable Long todoId, @PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(this.taskService.update(id, task, todoId));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long todoId, @PathVariable Long id) throws ResourceNotFoundException{
        this.taskService.delete(id);
    }
}