package com.agilesolutions.test.repository;

import com.agilesolutions.test.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> { }
