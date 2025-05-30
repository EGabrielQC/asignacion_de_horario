package com.proyectoh.asignacion_de_horario.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorarioRequest {
    private Integer docenteId;
    private Integer cursoSeccionId;
    private Integer aulaId;
    private Integer bloqueHorarioId;

}
