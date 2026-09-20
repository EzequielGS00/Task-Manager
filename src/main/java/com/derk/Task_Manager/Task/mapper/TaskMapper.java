package com.derk.Task_Manager.Task.mapper;

import com.derk.Task_Manager.Priority.entity.Priority;
import com.derk.Task_Manager.Status.entity.Status;
import com.derk.Task_Manager.Task.dto.request.CreateTaskDto;
import com.derk.Task_Manager.Task.dto.response.TaskResponseDto;
import com.derk.Task_Manager.Task.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task toEntity(
            CreateTaskDto dto,
            Status status,
            Priority priority
    ){
        Task task = new Task();

        task.setTituloTarea(dto.tituloTarea());
        task.setDescripcionTarea(dto.descripcionTarea());
        task.setEstadoTarea(status);
        task.setPrioridadTarea(priority);
        task.setFechaLimite(dto.fechaLimite());

        return task;
    }

    public TaskResponseDto toResponseDto(Task task) {
        return new TaskResponseDto(
                task.getUuidTask(),
                task.getTituloTarea(),
                task.getDescripcionTarea(),

                task.getEstadoTarea().getIdEstado(),
                task.getEstadoTarea().getTipoEstado(),

                task.getPrioridadTarea().getIdPrioridad(),
                task.getPrioridadTarea().getTipoPrioridad(),

                task.getFechaCreacion(),
                task.getFechaLimite(),
                task.getFechaFinalizacion()
        );
    }
}
