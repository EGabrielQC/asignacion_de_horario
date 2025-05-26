package com.proyectoh.asignacion_de_horario.controller;

import com.proyectoh.asignacion_de_horario.dto.request.AulaRequest;
import com.proyectoh.asignacion_de_horario.dto.request.CursoRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Curso")
public class CursoController {
    @Autowired
    private CursoService cursoService;
    //Hacer antes el Request
    @PostMapping
    public ResponseEntity<ApiResponse> createCurso(@RequestBody CursoRequest cursoRequest){

        ApiResponse apiResponse=cursoService.createCurso(cursoRequest);
        return ResponseEntity.ok(apiResponse);
    }
}
