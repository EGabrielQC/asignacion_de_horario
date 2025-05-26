package com.proyectoh.asignacion_de_horario.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoSeccionRequest {
    private Integer cursoId;
    private Integer seccionAcademicaId;
    private String modo;
}
