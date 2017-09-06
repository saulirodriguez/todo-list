package com.agilesolutions.test.repository;

import com.agilesolutions.test.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {
//    @Query("SELECT task FROM Task task WHERE task.owner.id = :ownerId and task.id = :id")
//    public Task findOneByOwner(@Param("ownerId") Long ownerId, @Param("id") Long id);
}
