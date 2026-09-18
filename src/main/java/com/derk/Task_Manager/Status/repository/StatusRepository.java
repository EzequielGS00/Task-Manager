package com.derk.Task_Manager.Status.repository;

import com.derk.Task_Manager.Status.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
