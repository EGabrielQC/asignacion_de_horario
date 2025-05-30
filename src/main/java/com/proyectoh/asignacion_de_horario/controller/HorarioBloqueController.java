package com.proyectoh.asignacion_de_horario.controller;

import com.proyectoh.asignacion_de_horario.dto.request.HorarioBloqueRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.HorarioBloqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/horarioBloque")
public class HorarioBloqueController {
    @Autowired
    HorarioBloqueService horarioBloqueService;
    //Seguir con el request y luego service para proseguir
    @PostMapping
    public ResponseEntity<ApiResponse> createBloqueHorario(@RequestBody HorarioBloqueRequest horarioBloqueRequest){
        ApiResponse apiResponse=horarioBloqueService.createHorarioBloque(horarioBloqueRequest);
        return ResponseEntity.ok(apiResponse);
    }

}
