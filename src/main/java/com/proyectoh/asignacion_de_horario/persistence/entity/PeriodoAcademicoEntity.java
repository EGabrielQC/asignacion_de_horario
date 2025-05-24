package com.proyectoh.asignacion_de_horario.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "periodo_academico")

public class PeriodoAcademicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private LocalDate fechaInicio; //Camelcase es el formato de escritura
    private LocalDate fechaFin;
    //Relacion de llegada desde SeccionAcademicaEntity
    @OneToMany(mappedBy = "periodoAcademico", fetch = FetchType.LAZY)
    private List<SeccionAcademicaEntity>seccionAcademicaEntities = new ArrayList<>();
}
