package com.proyectoh.asignacion_de_horario.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//Es quien responde
// Va tener dos atributos
//1er El mensaje
//2do El objeto a retornar
@Data//estamos creando getter y setter y varias cosas mas
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String mensaje;
    private Object data;

}
