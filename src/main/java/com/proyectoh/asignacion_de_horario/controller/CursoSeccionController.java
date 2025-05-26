package com.proyectoh.asignacion_de_horario.controller;

import com.proyectoh.asignacion_de_horario.dto.request.CursoSeccionRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.CursoSeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CursoSeccion")
public class CursoSeccionController {
    @Autowired
    private CursoSeccionService cursoSeccionService;
    //hacer el request antes de continuar
    @PostMapping
    public ResponseEntity<ApiResponse> createCursoSeccion(@RequestBody CursoSeccionRequest cursoSeccionRequest){
        ApiResponse apiResponse=cursoSeccionService.createCursoSeccion(cursoSeccionRequest);
        return ResponseEntity.ok(apiResponse);
    }


}
