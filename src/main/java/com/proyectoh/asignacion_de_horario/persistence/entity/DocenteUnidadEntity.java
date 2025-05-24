package com.proyectoh.asignacion_de_horario.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "docente_unidad")
public class DocenteUnidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    //Relacion principal DocenteEntity
    @ManyToOne(targetEntity = DocenteEntity.class, fetch = FetchType.LAZY)
    @JoinColumn (name = "docente_id")
    private DocenteEntity docente;
    //Relacion principal UnidadAcademicaEntity
    @ManyToOne(targetEntity = UnidadAcademicaEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name="unidad_academica_id")
    private UnidadAcademicaEntity unidadAcademica;
}
