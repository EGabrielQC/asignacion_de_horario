package com.proyectoh.asignacion_de_horario.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "disponibilidad_docente")
public class DisponibilidadDocenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    //Relacion Principal de DocenteEntity
    //Muchas disponibilidades pertenecen a un docente
    @ManyToOne(targetEntity = DocenteEntity.class, fetch = FetchType.LAZY)
    //referencias Id
    @JoinColumn (name = "docente_id")
    //copiar el nombre del atributo ejem. "docente"
    private DocenteEntity docente;
    private  String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String tipo;
}
