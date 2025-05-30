package com.proyectoh.asignacion_de_horario.controller;


import com.proyectoh.asignacion_de_horario.dto.request.HorarioRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/horario")
public class HorarioController {
    //Llamamos a HorarioService
    @Autowired
    private HorarioService horarioService;
    //Vamos al request - service y culminamos aqui
    @PostMapping
    public ResponseEntity<ApiResponse> createHorario(@RequestBody HorarioRequest horarioRequest){
        ApiResponse apiResponse=horarioService.createHorario(horarioRequest);
        return ResponseEntity.ok(apiResponse);
    }
}
