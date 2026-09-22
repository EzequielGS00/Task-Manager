package com.derk.Task_Manager.Task.service;

import com.derk.Task_Manager.Task.dto.request.CreateTaskDto;
import com.derk.Task_Manager.Task.dto.request.UpdateTaskDto;
import com.derk.Task_Manager.Task.dto.response.TaskResponseDto;

import java.util.List;
import java.util.UUID;

public interface ITaskService {

    TaskResponseDto createTask(CreateTaskDto dto);
    List<TaskResponseDto> getAllTask();
    TaskResponseDto getTaskById(UUID uuid);
    TaskResponseDto updateTask(UUID uuid, UpdateTaskDto dto);
    void deleteTask(UUID uuid);
}
