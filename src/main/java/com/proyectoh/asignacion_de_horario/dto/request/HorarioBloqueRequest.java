package com.proyectoh.asignacion_de_horario.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorarioBloqueRequest {
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String turno;
    //Ir a service
}
