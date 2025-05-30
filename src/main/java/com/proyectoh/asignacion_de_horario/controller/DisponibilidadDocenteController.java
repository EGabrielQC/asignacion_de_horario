package com.proyectoh.asignacion_de_horario.controller;

import com.proyectoh.asignacion_de_horario.dto.request.DisponibilidadDocenteRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.DisponibilidadDocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disponibilidad")
public class DisponibilidadDocenteController {
    @Autowired
    private DisponibilidadDocenteService disponibilidadDocenteService;
    //Hacer el request antes de proseguir.
    @PostMapping
    public ResponseEntity<ApiResponse> createDisponibilidadDocente(@RequestBody DisponibilidadDocenteRequest disponibilidadDocenteRequest){
        ApiResponse apiResponse=disponibilidadDocenteService.createDisponibilidadDocente(disponibilidadDocenteRequest);
        return ResponseEntity.ok(apiResponse);
    }
}
