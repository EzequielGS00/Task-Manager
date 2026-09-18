package com.derk.Task_Manager.Priority.entity;


import com.derk.Task_Manager.Task.entity.Task;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prioridad")
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prioridad")
    private Integer idPrioridad;
    @Column(name = "tipo_estado")
    private String tipoEstado;
    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "prioridadTarea")
    private List<Task> tasks;
}
