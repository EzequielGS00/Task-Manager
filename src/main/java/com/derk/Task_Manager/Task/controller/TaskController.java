package com.derk.Task_Manager.Task.controller;


import com.derk.Task_Manager.Task.dto.request.CreateTaskDto;
import com.derk.Task_Manager.Task.dto.request.UpdateTaskDto;
import com.derk.Task_Manager.Task.dto.request.UpdateTaskPriorityDto;
import com.derk.Task_Manager.Task.dto.request.UpdateTaskStatusDto;
import com.derk.Task_Manager.Task.dto.response.TaskResponseDto;
import com.derk.Task_Manager.Task.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks(){

        return ResponseEntity.ok(
                taskService.getAllTask()
        );
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable UUID uuid){

        return ResponseEntity.ok(taskService.getTaskById(uuid));
    }


    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(
            @Valid @RequestBody CreateTaskDto dto
            ){

        TaskResponseDto task = taskService.createTask(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(task);

    }

    @PutMapping("/{uuid}")
    public ResponseEntity<TaskResponseDto> updateTask(
            @PathVariable UUID uuid,
            @Valid @RequestBody UpdateTaskDto dto
            ){
        return ResponseEntity.ok(
                taskService.updateTask(uuid, dto)
        );
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable UUID uuid
    ){
        taskService.deleteTask(uuid);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{uuid}/status")
    public ResponseEntity<TaskResponseDto> updateTaskStatus(
            @PathVariable UUID uuid,
            @Valid @RequestBody UpdateTaskStatusDto dto
    ){
        return ResponseEntity.ok(
                taskService.updateTaskStatus(uuid, dto)
        );
    }

    @PatchMapping("/{uuid}/priority")
    public ResponseEntity<TaskResponseDto> updateTaskPriority(
            @PathVariable UUID uuid,
            @Valid @RequestBody UpdateTaskPriorityDto dto
    ){
        return  ResponseEntity.ok(
                taskService.updateTaskPrority(uuid, dto)
        );
    }

}
