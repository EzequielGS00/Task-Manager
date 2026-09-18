package com.derk.Task_Manager.Task.dto.response;

import com.derk.Task_Manager.Priority.entity.Priority;
import com.derk.Task_Manager.Status.entity.Status;

import java.time.LocalDate;
import java.util.UUID;

public record TaskResponseDto(
        UUID uuidTask,
        String tituloTarea,
        String descripcionTarea,
        Integer estadoTarea,
        String nombreTarea,
        Integer prioridadTarea,
        String nombrePrioridad,
        LocalDate fechaCreacion,
        LocalDate fechaLimite,
        LocalDate fechaFinalizacion
) {}
