package com.proyectoh.asignacion_de_horario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoSeccionDto {
    private Integer id;
    private String nombreCurso;
    private String nombreSeccion;
    private String modo;
}
