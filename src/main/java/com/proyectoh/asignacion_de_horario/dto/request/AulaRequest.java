package com.proyectoh.asignacion_de_horario.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Request es la peticion del cliente, es quien manda el nombre capidad del aula.
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AulaRequest {
    private String nombre;
    private Integer capacidad;
    private String tipo;
}
