package com.agilesolutions.test.service;

import com.agilesolutions.test.exception.ResourceNotFoundException;
import com.agilesolutions.test.model.ToDo;
import com.agilesolutions.test.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ToDoService {
    private LoggerManager logger = LoggerManager.getInstance();
    @Autowired
    private ToDoRepository toDoRepository;

    public List<ToDo> findAll(Optional<String> optionalName, Optional<String> optionalDesc) {
        List<ToDo> todoList = new ArrayList<>();

        this.toDoRepository
                .findAll()
                .forEach(todoList::add);

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

    public ToDo findOne(Long id) {
        return this.toDoRepository.findOne(id);
    }

    public ToDo create(ToDo toDo) {
        if(toDo.getDescription() == null) {
            toDo.setDescription("");
        }

        this.toDoRepository.save(toDo);
        return toDo;
    }

    public ToDo update(Long id, ToDo toDo) throws ResourceNotFoundException {
        ToDo t = this.toDoRepository.findOne(id);

        if(t == null) {
            throw new ResourceNotFoundException("ToDo");
        }

        if(!toDo.getName().isEmpty()) {
            t.setName(toDo.getName());
        }

        if(!toDo.getDescription().isEmpty()) {
            t.setDescription(toDo.getDescription());
        }

        this.toDoRepository.save(t);
        return t;
    }

    public void delete(Long id) {
        this.toDoRepository.delete(id);
    }
}
