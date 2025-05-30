package com.proyectoh.asignacion_de_horario.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisponibilidadDocenteRequest {
    private Integer docenteId;
    private  String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String tipo;
}
