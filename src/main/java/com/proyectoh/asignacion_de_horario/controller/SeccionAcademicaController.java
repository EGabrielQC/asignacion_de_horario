package com.proyectoh.asignacion_de_horario.controller;

import com.proyectoh.asignacion_de_horario.dto.SeccionAcademicaDto;
import com.proyectoh.asignacion_de_horario.dto.request.PeriodoAcademicoRequest;
import com.proyectoh.asignacion_de_horario.dto.request.SeccionAcademicaRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.SeccionAcademicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    // Listar Secciones Académicas
    @GetMapping
    public ResponseEntity<List<SeccionAcademicaDto>> listarSeccionesAcademicas() {
        List<SeccionAcademicaDto> lista = seccionAcademicaService.listarSeccionesAcademicas();
        return ResponseEntity.ok(lista);
    }

    // Eliminar sección académica
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminar(@PathVariable Integer id) {
        ApiResponse response = seccionAcademicaService.deleteSeccionAcademica(id);
        return ResponseEntity.ok(response);
    }
    // Actualizar sección académica
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizar(@PathVariable Integer id, @RequestBody SeccionAcademicaRequest request) {
        ApiResponse response = seccionAcademicaService.updateSeccionAcademica(id, request);
        return ResponseEntity.ok(response);
    }
    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<SeccionAcademicaDto> buscarPorId(@PathVariable Integer id) {
        SeccionAcademicaDto dto = seccionAcademicaService.findById(id);
        return ResponseEntity.ok(dto);
    }
}
