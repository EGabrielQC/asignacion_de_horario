package com.proyectoh.asignacion_de_horario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//Capa de transferencia de datos
//convertimos AulaEntity en AulaDto
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AulaDto {
    private Integer id;
    private String nombre;
    private Integer capacidad;
    private String tipo;
}
