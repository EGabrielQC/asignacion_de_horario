package com.proyectoh.asignacion_de_horario.controller;

import com.proyectoh.asignacion_de_horario.dto.request.PeriodoAcademicoRequest;
import com.proyectoh.asignacion_de_horario.dto.request.SeccionAcademicaRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.SeccionAcademicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seccion")
public class SeccionAcademicaController {
    @Autowired
    private SeccionAcademicaService seccionAcademicaService;
    //Vamos al request - service y culminamos aqui
    @PostMapping
    public ResponseEntity<ApiResponse> createSeccionAcademica(@RequestBody SeccionAcademicaRequest seccionAcademicaRequest){
        ApiResponse apiResponse=seccionAcademicaService.createSeccionAcademica(seccionAcademicaRequest);
        return ResponseEntity.ok(apiResponse);
    }
}
