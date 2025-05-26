package com.proyectoh.asignacion_de_horario.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CursoRequest {
    private String nombre;
    private String horasSemanales;
    private String tipo;
}
