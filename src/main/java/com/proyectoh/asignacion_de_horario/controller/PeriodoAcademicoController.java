package com.proyectoh.asignacion_de_horario.controller;

import com.proyectoh.asignacion_de_horario.dto.PeriodoAcademicaDto;
import com.proyectoh.asignacion_de_horario.dto.request.PeriodoAcademicoRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.PeriodoAcademicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    // Endpoint para listar todos los periodos académicos
    @GetMapping
    public ResponseEntity<List<PeriodoAcademicaDto>> listarPeriodos() {
        List<PeriodoAcademicaDto> periodos = periodoAcademicoService.listarPeriodos();
        return ResponseEntity.ok(periodos);
    }
    // Eliminar periodo
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePeriodo(@PathVariable Integer id) {
        ApiResponse response = periodoAcademicoService.deletePeriodo(id);
        return ResponseEntity.ok(response);
    }
    // Actualizar periodo
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePeriodo(@PathVariable Integer id,
                                                     @RequestBody PeriodoAcademicoRequest request) {
        ApiResponse response = periodoAcademicoService.updatePeriodo(id, request);
        return ResponseEntity.ok(response);
    }
    // Buscar periodo por ID
    @GetMapping("/{id}")
    public ResponseEntity<PeriodoAcademicaDto> getPeriodoById(@PathVariable Integer id) {
        PeriodoAcademicaDto dto = periodoAcademicoService.findById(id);
        return ResponseEntity.ok(dto);
    }

}

