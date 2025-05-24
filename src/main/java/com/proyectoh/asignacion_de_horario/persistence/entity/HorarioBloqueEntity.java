package com.proyectoh.asignacion_de_horario.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "horario_bloque")

public class HorarioBloqueEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String turno;
    //Relacion de Llegada desde HorarioEntity
    @OneToMany(mappedBy = "bloqueHorario", fetch = FetchType.LAZY)
    private List<HorarioEntity>horarioEntities = new ArrayList<>();

}
