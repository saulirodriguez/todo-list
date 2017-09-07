package com.agilesolutions.test.controller;

import com.agilesolutions.test.exception.BadRequestException;
import com.agilesolutions.test.exception.ResourceNotFoundException;
import com.agilesolutions.test.model.Task;
import com.agilesolutions.test.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo-list/{todoId}/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Task>> findAll(@PathVariable Long todoId, @RequestParam(required = false) String name, @RequestParam(required = false) String description) throws ResourceNotFoundException {
            return ResponseEntity
                    .ok(this.taskService.findAll(todoId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) throws ResourceNotFoundException {
        Task task = this.taskService.findById(id);

        return ResponseEntity.ok(task);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Task> create(@RequestBody Task task, @PathVariable Long todoId) throws BadRequestException, ResourceNotFoundException {
        if (task.getName() == null || task.getName().isEmpty()) {
            throw new BadRequestException("Task", "Required Field: name");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(this.taskService.save(task, todoId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Task> update(@RequestBody Task task, @PathVariable Long todoId, @PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(this.taskService.update(id, task, todoId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable Long todoId, @PathVariable Long id) throws ResourceNotFoundException{
        this.taskService.delete(id);
    }
}