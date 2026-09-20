package com.derk.Task_Manager.Task.service;

import com.derk.Task_Manager.Priority.entity.Priority;
import com.derk.Task_Manager.Priority.repository.PriorityRepository;
import com.derk.Task_Manager.Status.entity.Status;
import com.derk.Task_Manager.Status.repository.StatusRepository;
import com.derk.Task_Manager.Task.dto.request.CreateTaskDto;
import com.derk.Task_Manager.Task.dto.response.TaskResponseDto;
import com.derk.Task_Manager.Task.entity.Task;
import com.derk.Task_Manager.Task.exception.PriorityNotFoundException;
import com.derk.Task_Manager.Task.exception.StatusNotFoundException;
import com.derk.Task_Manager.Task.mapper.TaskMapper;
import com.derk.Task_Manager.Task.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService{

    private final TaskRepository taskRepository;
    private final StatusRepository statusRepository;
    private final PriorityRepository priorityRepository;
    private final TaskMapper taskMapper;


    @Override
    @Transactional
    public TaskResponseDto createTask(CreateTaskDto dto) {

        Status status = statusRepository.findById(dto.idEstado())
                .orElseThrow(() ->
                        new StatusNotFoundException(dto.idEstado())
                );

        Priority priority = priorityRepository.findById(dto.idPrioridad())
                .orElseThrow(()->
                        new PriorityNotFoundException(dto.idPrioridad())
                );

        Task task = taskMapper.toEntity(
                dto,
                status,
                priority
        );
        task.setFechaCreacion(LocalDate.now());

        Task savedTask = taskRepository.save(task);

        return taskMapper.toResponseDto(savedTask);
    }
}
