package com.agilesolutions.test.controller;

import com.agilesolutions.test.exception.BadRequestException;
import com.agilesolutions.test.exception.ResourceNotFoundException;
import com.agilesolutions.test.model.ToDo;
import com.agilesolutions.test.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo-list")
public class ToDoController {
    @Autowired
    private ToDoService toDoService;

    @GetMapping
    public ResponseEntity<List<ToDo>> get(@RequestParam(required = false) String name, @RequestParam(required = false) String description) {
        if(name != null || description != null) {
            return ResponseEntity
                    .ok(this.toDoService.find(Optional.ofNullable(name), Optional.ofNullable(description)));
        }

        return ResponseEntity
                    .ok(this.toDoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getById(@PathVariable Long id) throws ResourceNotFoundException {
        ToDo todo = this.toDoService.findById(id);

        return ResponseEntity.ok(todo);
    }

    @PostMapping
    public ResponseEntity<ToDo> create(@RequestBody ToDo toDo) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.toDoService.save(toDo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDo> update(@RequestBody ToDo toDo, @PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(this.toDoService.update(id, toDo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ToDo> delete(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(this.toDoService.delete(id));
    }
}