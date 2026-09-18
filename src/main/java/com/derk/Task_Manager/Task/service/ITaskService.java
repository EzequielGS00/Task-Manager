package com.derk.Task_Manager.Task.service;

import com.derk.Task_Manager.Task.dto.request.CreateTaskDto;
import com.derk.Task_Manager.Task.dto.response.TaskResponseDto;

public interface ITaskService {

    TaskResponseDto createTask(CreateTaskDto dto);
}
