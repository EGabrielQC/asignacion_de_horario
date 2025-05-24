package com.proyectoh.asignacion_de_horario.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "docente")
public class DocenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;
    private Integer horaContratadas;
    private Integer maxHorasPorDia;
    //Relacion de Llegada desde DisponibilidadDocenteEntity
    @OneToMany(mappedBy = "docente", fetch = FetchType.LAZY)
    private List<DisponibilidadDocenteEntity>disponibilidadDocenteEntities = new ArrayList<>();

    //relacion de llegada desde DocenteUnidadEntity
    @OneToMany(mappedBy = "docente", fetch = FetchType.LAZY)
    private List<DocenteUnidadEntity>docenteUnidadEntities = new ArrayList<>();

    //Relacion inver de HorarioEntity - Docente
    @OneToMany(mappedBy = "docente", fetch = FetchType.LAZY)
    private List<HorarioEntity>horarioEntities = new ArrayList<>();

}
