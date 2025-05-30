package com.proyectoh.asignacion_de_horario.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocenteRequest {
    //El id ya se pone de manera automatica.
    private String nombre;
    private String apellido;
    private String correo;
    private Integer horaContratadas;
    private Integer maxHorasPorDia;
}
