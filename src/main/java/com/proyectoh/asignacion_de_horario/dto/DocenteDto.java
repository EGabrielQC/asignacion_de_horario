package com.proyectoh.asignacion_de_horario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocenteDto {
    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;
    private Integer horaContratadas;
    private Integer maxHorasPorDia;
}
