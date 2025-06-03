package com.proyectoh.asignacion_de_horario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PeriodoAcademicaDto {
    private Integer id;
    private String nombre;
    private LocalDate fechaInicio; //Camelcase es el formato de escritura
    private LocalDate fechaFin;
}
