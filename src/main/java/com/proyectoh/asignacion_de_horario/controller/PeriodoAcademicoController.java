package com.proyectoh.asignacion_de_horario.controller;

import com.proyectoh.asignacion_de_horario.dto.request.PeriodoAcademicoRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.PeriodoAcademicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/periodo")
public class PeriodoAcademicoController {
    @Autowired
    private PeriodoAcademicoService periodoAcademicoService;
    //Vamos al request - service y culminamos aqui
    @PostMapping
    public ResponseEntity<ApiResponse> createPeriodoAcademico(@RequestBody PeriodoAcademicoRequest periodoAcademicoRequest){
        ApiResponse apiResponse=periodoAcademicoService.createPeriodoAcademico(periodoAcademicoRequest);
        return ResponseEntity.ok(apiResponse);
    }
}
