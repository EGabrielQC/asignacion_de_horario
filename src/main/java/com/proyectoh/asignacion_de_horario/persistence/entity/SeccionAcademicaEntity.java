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
@Table(name = "seccion_academica")
public class SeccionAcademicaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    //Seccion Principal para la tabla PeriodoAcademicoEntity
    @ManyToOne(targetEntity = PeriodoAcademicoEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name="periodo_academico_id")
    private PeriodoAcademicoEntity periodoAcademico;
    //Relacion de llegada desde CursoSeccionEntity
    @OneToMany(mappedBy = "seccionAcademica", fetch = FetchType.LAZY)
    private List<CursoSeccionEntity>cursoSeccionEntities = new ArrayList<>();
}
