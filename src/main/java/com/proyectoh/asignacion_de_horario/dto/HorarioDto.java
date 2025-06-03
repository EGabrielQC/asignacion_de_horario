package com.proyectoh.asignacion_de_horario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class HorarioDto {
    private Integer id;
    private String nombreDocente;
    private String nombreCursoSeccion;
    private String nombreAula;
    private String nombreBloqueHorario;
}
