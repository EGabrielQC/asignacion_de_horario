package com.proyectoh.asignacion_de_horario.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeriodoAcademicoRequest {
    private String nombre;
    private LocalDate fechaInicio; //Camelcase es el formato de escritura
    private LocalDate fechaFin;
}
