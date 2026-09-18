package com.derk.Task_Manager.Task.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateTaskDto(

        @NotBlank(message = "El título es obligatorio")
        @Size(max = 100, message = "El título no puede superar los 100 caracteres")
        String tituloTarea,
        @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
        String descripcionTarea,
        @NotNull(message = "El estado es obligatorio")
        Integer idEstado,
        @NotNull(message = "La prioridad es obligatorio")
        Integer idPrioridad,
        @NotNull(message = "La fecha límite es obligatoria")
        @FutureOrPresent(message = "La fecha límite no puede estar en el pasado")
        LocalDate fechaLimite
) {
}
