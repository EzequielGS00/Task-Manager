package com.derk.Task_Manager.Task.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateTaskPriorityDto(
        @NotNull
        Integer idPrioridad
) {
}
