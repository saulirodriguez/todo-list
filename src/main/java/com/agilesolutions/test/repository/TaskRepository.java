package com.agilesolutions.test.repository;

import com.agilesolutions.test.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findByOwnerId(Long ownerId);
}
