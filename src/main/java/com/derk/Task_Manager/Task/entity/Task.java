package com.derk.Task_Manager.Task.entity;


import com.derk.Task_Manager.Priority.entity.Priority;
import com.derk.Task_Manager.Status.entity.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tareas")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid_tarea", nullable = false, updatable = false)
    private UUID uuidTask;

    @Column(name = "titulo_tarea", nullable = false, length = 100)
    private String tituloTarea;

    @Column(name = "descripcion_tarea")
    private String descripcionTarea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", nullable = false)
    private Status estadoTarea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prioridad", nullable = false)
    private Priority prioridadTarea;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "fecha_limite")
    private LocalDate fechaLimite;

    @Column(name = "fecha_finalizacion")
    private LocalDate fechaFinalizacion;
}
