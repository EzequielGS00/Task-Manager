package com.derk.Task_Manager.Task.controller;


import com.derk.Task_Manager.Task.dto.request.CreateTaskDto;
import com.derk.Task_Manager.Task.dto.request.UpdateTaskDto;
import com.derk.Task_Manager.Task.dto.request.UpdateTaskPriorityDto;
import com.derk.Task_Manager.Task.dto.request.UpdateTaskStatusDto;
import com.derk.Task_Manager.Task.dto.response.TaskResponseDto;
import com.derk.Task_Manager.Task.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(
            summary = "Obtener todas las tareas",
            description = "Obtiene la lista completa de tareas registradas"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de tareas obtenida correctamente"
            )
    })
    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks(){

        return ResponseEntity.ok(
                taskService.getAllTask()
        );
    }


    @Operation(
            summary = "Obtener una tarea",
            description = "Obtiene la informacion de una sola tarea"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Tarea obtenida correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tarea no encontrada"
            )
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable UUID uuid){

        return ResponseEntity.ok(taskService.getTaskById(uuid));
    }


    @Operation(
            summary = "Crear una tarea",
            description = "Registra una nueva tarea asociándola a un estado y una prioridad"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Tarea Creada Correctamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Los datos enviados no son validos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "El estado o la prioridad especificados no existen"
            )
    })
    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(
            @Valid @RequestBody CreateTaskDto dto
            ){

        TaskResponseDto task = taskService.createTask(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(task);

    }

    @Operation(
            summary = "Actualizar una tarea",
            description = "Actualiza la información de una tarea existente utilizando su UUID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Tarea actualizada correctamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Los datos enviados no son válidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La tarea, el estado o la prioridad no existen"
            )
    })
    @PutMapping("/{uuid}")
    public ResponseEntity<TaskResponseDto> updateTask(
            @PathVariable UUID uuid,
            @Valid @RequestBody UpdateTaskDto dto
            ){
        return ResponseEntity.ok(
                taskService.updateTask(uuid, dto)
        );
    }


    @Operation(
            summary = "Eliminar una tarea",
            description = "Elimina permanentemente una tarea utilizando su UUID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Tarea eliminada correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tarea no encontrada"
            )
    })
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable UUID uuid
    ){
        taskService.deleteTask(uuid);
        return ResponseEntity.noContent().build();
    }



    @Operation(
            summary = "Actualizar estado de una tarea",
            description = "Actualiza únicamente el estado de una tarea existente utilizando su UUID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Estado de la tarea actualizado correctamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "El estado enviado no es válido"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La tarea o el estado no existen"
            )
    })
    @PatchMapping("/{uuid}/status")
    public ResponseEntity<TaskResponseDto> updateTaskStatus(
            @PathVariable UUID uuid,
            @Valid @RequestBody UpdateTaskStatusDto dto
    ){
        return ResponseEntity.ok(
                taskService.updateTaskStatus(uuid, dto)
        );
    }



    @Operation(
            summary = "Actualizar prioridad de una tarea",
            description = "Actualiza únicamente la prioridad de una tarea existente utilizando su UUID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Prioridad de la tarea actualizada correctamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "La prioridad enviada no es válida"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "La tarea o la prioridad no existen"
            )
    })
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
