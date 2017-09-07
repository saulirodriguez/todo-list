package com.agilesolutions.test.service;

import com.agilesolutions.test.exception.BadRequestException;
import com.agilesolutions.test.exception.ResourceNotFoundException;
import com.agilesolutions.test.model.ToDo;
import com.agilesolutions.test.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;

    public List<ToDo> findAll() {
        return this.toDoRepository.findAll();
    }

    public List<ToDo> find(Optional<String> optionalName, Optional<String> optionalDesc) {
        List<ToDo> todoList = this.toDoRepository.findAll();

        if(optionalName.isPresent()) {
            String name = optionalName.get().toLowerCase();
            todoList = todoList.stream()
                    .filter(toDo -> toDo.getName().toLowerCase().contains(name))
                    .collect(Collectors.toList());
        }

        if(optionalDesc.isPresent()) {
            String description = optionalDesc.get().toLowerCase();
            todoList = todoList.stream()
                    .filter(toDo -> toDo.getDescription().toLowerCase().contains(description))
                    .collect(Collectors.toList());
        }

        return todoList;
    }

    public ToDo findById(Long id) throws ResourceNotFoundException {
        ToDo todo = this.toDoRepository.findOne(id);
        if(todo == null) {
            throw new ResourceNotFoundException("ToDo", id);
        }

        return todo;
    }

    public ToDo save(ToDo toDo) throws BadRequestException {
        if (toDo.getName() == null || toDo.getName().isEmpty()) {
            throw new BadRequestException("ToDo List", "Required Field: name");
        }

        return this.toDoRepository.save(toDo);
    }

    public ToDo update(Long id, ToDo updatedTodo) throws ResourceNotFoundException {
        ToDo existentTodo = this.findById(id);
        existentTodo.setName(updatedTodo.getName());
        existentTodo.setDescription(updatedTodo.getDescription());

        return this.toDoRepository.save(existentTodo);
    }

    public ToDo delete(Long id) throws ResourceNotFoundException {
        ToDo deletedTodo = this.findById(id);
        this.toDoRepository.delete(deletedTodo);
        return deletedTodo;
    }
}
