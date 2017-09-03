package com.agilesolutions.test.repository;

import com.agilesolutions.test.model.ToDo;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<ToDo, Long> { }
