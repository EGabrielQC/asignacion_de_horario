package com.proyectoh.asignacion_de_horario.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//Anotaciones :D
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "unidad_academica")

public class UnidadAcademicaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    //Relacion de Llegada desde la tabla Cursos
    @OneToMany (mappedBy="unidadAcademica", cascade = CascadeType.MERGE, fetch=FetchType.LAZY)
    private List<CursoEntity> cursos = new ArrayList<>();
    //Inversa de Docente unidad
    @OneToMany(mappedBy = "unidadAcademica", fetch = FetchType.LAZY)
    private List<DocenteUnidadEntity>docenteUnidadEntities = new ArrayList<>(); //para inicializar
}
