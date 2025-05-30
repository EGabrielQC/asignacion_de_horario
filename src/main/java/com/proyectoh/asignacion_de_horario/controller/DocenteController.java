package com.proyectoh.asignacion_de_horario.controller;

import com.proyectoh.asignacion_de_horario.dto.request.DocenteRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/docente")
public class DocenteController {
    @Autowired
    private DocenteService docenteService;
    //Hacer el request y completar la lógica en service antes de proseguir
    @PostMapping
    public ResponseEntity<ApiResponse> createDocente(@RequestBody DocenteRequest docenteRequest){
        ApiResponse apiResponse=docenteService.createDocente(docenteRequest);
        return ResponseEntity.ok(apiResponse);
    }
}
