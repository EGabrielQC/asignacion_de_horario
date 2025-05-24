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
@Table(name = "curso_seccion")
public class CursoSeccionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Relación Principal con CursoEntity
    @ManyToOne(targetEntity = CursoEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;

    //relacion principal SeccionAcademicaEntity
    @ManyToOne(targetEntity = SeccionAcademicaEntity.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "seccion_academica_id")
    private SeccionAcademicaEntity seccionAcademica;
    private String modo;

    //Relacion de llegada de HorarioEntity;?????
    @OneToMany(mappedBy = "cursoSeccion", fetch = FetchType.LAZY)
    private List<HorarioEntity>horarioEntities = new ArrayList<>();
}
