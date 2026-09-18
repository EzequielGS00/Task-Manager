package com.derk.Task_Manager.Status.entity;


import com.derk.Task_Manager.Task.entity.Task;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "estado")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Integer idEstado;
    @Column(name = "tipo_estado")
    private String tipoEstado;
    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "estadoTarea")
    private List<Task> tasks;
}
