package com.derk.Task_Manager.Task.dto.request;

import java.time.LocalDate;

public record CreateTaskDto(
        String tituloTarea,
        String descripcionTarea,
        Integer idEstado,
        Integer idPrioridad,
        LocalDate fechaLimite
) {
}
