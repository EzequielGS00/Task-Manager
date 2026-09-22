package com.derk.Task_Manager.Task.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateTaskDto(
        @NotBlank(message = "El titulo es obligatorio")
        @Size(max = 100, message = "El titulo no puede superar los 100 caracteres")
        String tituloTarea,

        @Size(max = 500, message = "La descripcion no puedes superar los 500 caracteres")
        String descripcionTarea,
        @NotNull(message = "El estado es obligatorio")
        Integer idEstado,
        @NotNull(message = "La prioridad es obligatoria")
        Integer idPrioridad,
        @NotNull(message = "La fecha límite es obligatoria")
        LocalDate fechaLimite
) {
}
