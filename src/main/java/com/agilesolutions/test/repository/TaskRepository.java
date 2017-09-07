package com.agilesolutions.test.repository;

import com.agilesolutions.test.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Transactional(readOnly = true)
    List<Task> findByOwnerId(Long ownerId);
}
