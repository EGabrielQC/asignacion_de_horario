package com.proyectoh.asignacion_de_horario.controller;

import com.proyectoh.asignacion_de_horario.dto.request.UnidadAcademicaRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.UnidadAcademicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unidadAcademica")
public class UnidadAcademicaController {
    @Autowired
    private UnidadAcademicaService unidadAcademicaService;
    //Ir request
    @PostMapping
    public ResponseEntity<ApiResponse> createUnidadAcademica(@RequestBody UnidadAcademicaRequest unidadAcademicaRequest){
        ApiResponse apiResponse=unidadAcademicaService.createUnidadAcademica(unidadAcademicaRequest);
        return ResponseEntity.ok(apiResponse);
    }
}
