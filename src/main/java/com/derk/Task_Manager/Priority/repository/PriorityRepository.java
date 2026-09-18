package com.derk.Task_Manager.Priority.repository;

import com.derk.Task_Manager.Priority.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {
}
