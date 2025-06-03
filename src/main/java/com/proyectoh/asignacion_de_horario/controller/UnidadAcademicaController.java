package com.proyectoh.asignacion_de_horario.controller;

import com.proyectoh.asignacion_de_horario.dto.UnidadAcademicaDto;
import com.proyectoh.asignacion_de_horario.dto.request.UnidadAcademicaRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.UnidadAcademicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public ResponseEntity<List<UnidadAcademicaDto>> listarUnidadesAcademicas() {
        List<UnidadAcademicaDto> lista = unidadAcademicaService.listarUnidadesAcademicas();
        return ResponseEntity.ok(lista);
    }
    // Eliminar unidad académica
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminarUnidad(@PathVariable Integer id) {
        ApiResponse response = unidadAcademicaService.deleteUnidadAcademica(id);
        return ResponseEntity.ok(response);
    }
    // Actualizar unidad académica
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizarUnidad(@PathVariable Integer id, @RequestBody UnidadAcademicaRequest request) {
        ApiResponse response = unidadAcademicaService.updateUnidadAcademica(id, request);
        return ResponseEntity.ok(response);
    }
    // Buscar unidad académica por ID
    @GetMapping("/{id}")
    public ResponseEntity<UnidadAcademicaDto> buscarPorId(@PathVariable Integer id) {
        UnidadAcademicaDto dto = unidadAcademicaService.findById(id);
        return ResponseEntity.ok(dto);
    }

}
