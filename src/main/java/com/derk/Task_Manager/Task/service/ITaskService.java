package com.derk.Task_Manager.Task.service;

import com.derk.Task_Manager.Task.dto.request.CreateTaskDto;
import com.derk.Task_Manager.Task.dto.response.TaskResponseDto;

import java.util.List;

public interface ITaskService {

    TaskResponseDto createTask(CreateTaskDto dto);
    List<TaskResponseDto> getAllTask();
}
