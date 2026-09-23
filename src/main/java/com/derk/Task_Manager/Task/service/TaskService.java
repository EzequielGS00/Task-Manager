package com.derk.Task_Manager.Task.service;

import com.derk.Task_Manager.Priority.entity.Priority;
import com.derk.Task_Manager.Priority.repository.PriorityRepository;
import com.derk.Task_Manager.Status.entity.Status;
import com.derk.Task_Manager.Status.repository.StatusRepository;
import com.derk.Task_Manager.Task.dto.request.CreateTaskDto;
import com.derk.Task_Manager.Task.dto.request.UpdateTaskDto;
import com.derk.Task_Manager.Task.dto.request.UpdateTaskPriorityDto;
import com.derk.Task_Manager.Task.dto.request.UpdateTaskStatusDto;
import com.derk.Task_Manager.Task.dto.response.TaskResponseDto;
import com.derk.Task_Manager.Task.entity.Task;
import com.derk.Task_Manager.Task.exception.PriorityNotFoundException;
import com.derk.Task_Manager.Task.exception.StatusNotFoundException;
import com.derk.Task_Manager.Task.exception.TaskNotFoundException;
import com.derk.Task_Manager.Task.mapper.TaskMapper;
import com.derk.Task_Manager.Task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


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

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponseDto> getAllTask() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponseDto getTaskById(UUID uuid) {

        Task task = taskRepository.findById(uuid)
                .orElseThrow(() ->
                        new TaskNotFoundException(uuid));


        return taskMapper.toResponseDto(task);
    }

    @Override
    @Transactional
    public TaskResponseDto updateTask(UUID uuid, UpdateTaskDto dto) {

        Task task = taskRepository.findById(uuid)
                .orElseThrow(() ->
                        new TaskNotFoundException(uuid)
                );

        Status status = statusRepository.findById(dto.idEstado())
                .orElseThrow(() ->
                        new StatusNotFoundException(dto.idEstado()));

        Priority priority = priorityRepository.findById(dto.idPrioridad())
                .orElseThrow(() ->
                        new PriorityNotFoundException(dto.idPrioridad()));

        taskMapper.updateEntity(
                task,
                dto,
                status,
                priority
        );

        //Task updateTask = taskRepository.save(task);
        return taskMapper.toResponseDto(task);
    }

    @Override
    @Transactional
    public void deleteTask(UUID uuid) {
        Task task = taskRepository.findById(uuid)
                .orElseThrow(()->
                        new TaskNotFoundException(uuid));

        taskRepository.delete(task);
    }

    @Override
    @Transactional
    public TaskResponseDto updateTaskStatus(UUID uuid, UpdateTaskStatusDto dto) {

        Task task = taskRepository.findById(uuid)
                .orElseThrow(() ->
                        new TaskNotFoundException(uuid));
        Status status = statusRepository.findById(dto.idEstado())
                .orElseThrow(() ->
                        new StatusNotFoundException(dto.idEstado())
                );

        task.setEstadoTarea(status);

        Task updateTask = taskRepository.save(task);
        return taskMapper.toResponseDto(updateTask);
    }

    @Override
    @Transactional
    public TaskResponseDto updateTaskPrority(UUID uuid, UpdateTaskPriorityDto dto) {

        Task task = taskRepository.findById(uuid)
                .orElseThrow(()->
                        new TaskNotFoundException(uuid));

        Priority priority = priorityRepository.findById(dto.idPrioridad())
                .orElseThrow(() ->
                        new PriorityNotFoundException(dto.idPrioridad()));

        Task updateTask = taskRepository.save(task);

        return taskMapper.toResponseDto(updateTask);
    }
}
