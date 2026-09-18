package com.derk.Task_Manager.Task.repository;

import com.derk.Task_Manager.Task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}
