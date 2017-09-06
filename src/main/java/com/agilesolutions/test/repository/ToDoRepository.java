package com.agilesolutions.test.repository;

import com.agilesolutions.test.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> { }
