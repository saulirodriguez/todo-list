package com.agilesolutions.test.service;

import com.agilesolutions.test.exception.BadRequestException;
import com.agilesolutions.test.exception.ResourceNotFoundException;
import com.agilesolutions.test.entity.ToDo;
import com.agilesolutions.test.repository.ToDoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ToDoService {
    private static final Logger LOGGER = Logger.getLogger(ToDoService.class);
    @Autowired
    private ToDoRepository toDoRepository;
    private PublisherService publisherService;

    public ToDoService() {
        this.publisherService = new PublisherService();
    }


    @Transactional(readOnly = true)
    public List<ToDo> findAll() {
        return this.toDoRepository.findAll();
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public ToDo findById(Long id) throws ResourceNotFoundException {
        ToDo todo = this.toDoRepository.findOne(id);
        if(todo == null) {
            throw new ResourceNotFoundException("ToDo", id);
        }

        return todo;
    }

    @Transactional
    public ToDo save(ToDo toDo) throws BadRequestException, Exception {
        if (toDo.getName() == null || toDo.getName().isEmpty()) {
            throw new BadRequestException("ToDo List", "Required Field: name");
        }

        ToDo saved = this.toDoRepository.save(toDo);
        try {
            this.publisherService.sendMessage("SAVED: " + saved.getName());
        } catch (Exception e) {
            LOGGER.debug("ERROR SENDING MESSAGE", e);
        }

        return saved;
    }

    @Transactional
    public ToDo update(Long id, ToDo updatedTodo) throws ResourceNotFoundException, Exception {
        ToDo existentTodo = this.findById(id);
        existentTodo.setName(updatedTodo.getName());
        existentTodo.setDescription(updatedTodo.getDescription());

        ToDo saved = this.toDoRepository.save(existentTodo);

        try {
            this.publisherService.sendMessage("UPDATED: " + existentTodo.getName());
        } catch (Exception e) {
            System.out.println(e);
        }
        return saved;
    }

    @Transactional
    public ToDo delete(Long id) throws ResourceNotFoundException, Exception {
        ToDo deletedTodo = this.findById(id);
        this.toDoRepository.delete(deletedTodo);

        try {
            this.publisherService.sendMessage("DELETED: " + deletedTodo.getName());
        } catch (Exception e) {
            System.out.println(e);
        }
        return deletedTodo;
    }
}
