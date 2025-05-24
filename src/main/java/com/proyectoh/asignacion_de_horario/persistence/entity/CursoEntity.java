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
@Table(name = "curso")
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String horasSemanales;
    private String tipo;

    //relación principal de UnidadAcademicaEntity en CursoEntity
    @ManyToOne(targetEntity = UnidadAcademicaEntity.class,fetch = FetchType.LAZY)//Muchgos curos pueden pertenecer s una unidad academica//Traget hasta donde afectará//FetchType Lazzy  carga un curso.
    @JoinColumn(name = "unidad_academica_id")// esto es para nombrar el id de la unidadAcademica
    private UnidadAcademicaEntity unidadAcademica;

    //Relacion de llegada de la tabla CursoSeccionEntity
    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private List<CursoSeccionEntity>cursoSeccionEntities = new ArrayList<>();

}
