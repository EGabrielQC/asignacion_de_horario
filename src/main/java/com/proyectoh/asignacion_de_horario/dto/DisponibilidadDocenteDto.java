package com.proyectoh.asignacion_de_horario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisponibilidadDocenteDto {
    private Integer id;
    private String docenteNombre;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String tipo;
}
