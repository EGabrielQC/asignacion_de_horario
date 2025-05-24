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
@Table (name = "asignacion_horario")
public class HorarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //parametro()
    @ManyToOne(targetEntity = DocenteEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "docente_id")
    private DocenteEntity docente;

    //ManyToOne, etc se llama relaciones
    //Relacion Principal
    @ManyToOne(targetEntity = CursoSeccionEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_seccion_id")
    private CursoSeccionEntity cursoSeccion;
    //Relacion principal con la tabla Aula
    @ManyToOne(targetEntity = AulaEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "aula_id")
    private AulaEntity aula;
    //
    //Relacion Principal con la tabla HorarioBloqueEntity
    @ManyToOne(targetEntity = HorarioBloqueEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name="horario_bloque_id")
    private HorarioBloqueEntity bloqueHorario;

}
