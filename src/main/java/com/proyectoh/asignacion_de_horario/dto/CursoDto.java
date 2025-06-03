package com.proyectoh.asignacion_de_horario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDto {
    private Integer id;
    private String nombre;
    private String horasSemanales;
    private String tipo;
    private String nombreCurso;
}
