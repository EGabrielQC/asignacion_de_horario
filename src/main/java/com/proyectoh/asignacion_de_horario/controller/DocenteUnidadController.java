package com.proyectoh.asignacion_de_horario.controller;

import com.proyectoh.asignacion_de_horario.dto.request.DocenteUnidadRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.DocenteUnidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/docenteUnidad")
public class DocenteUnidadController {
    @Autowired
    private DocenteUnidadService docenteUnidadService;
    //Seguir con el request y luego service para proseguir
    @PostMapping
    public ResponseEntity<ApiResponse> createDocenteUnidad(@RequestBody DocenteUnidadRequest docenteUnidadRequest){
        ApiResponse apiResponse=docenteUnidadService.createDocenteUnidad(docenteUnidadRequest);
        return ResponseEntity.ok(apiResponse);
    }

}
