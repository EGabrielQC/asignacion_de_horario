package com.proyectoh.asignacion_de_horario.controller;

import com.proyectoh.asignacion_de_horario.dto.DocenteUnidadDto;
import com.proyectoh.asignacion_de_horario.dto.request.DocenteUnidadRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.DocenteUnidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    // Listar todas las asignaciones Docente-Unidad
    @GetMapping
    public ResponseEntity<List<DocenteUnidadDto>> listarDocenteUnidad() {
        List<DocenteUnidadDto> lista = docenteUnidadService.listarDocenteUnidad();
        return ResponseEntity.ok(lista);
    }
    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminarDocenteUnidad(@PathVariable Integer id) {
        ApiResponse response = docenteUnidadService.deleteDocenteUnidad(id);
        return ResponseEntity.ok(response);
    }
    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizarDocenteUnidad(@PathVariable Integer id, @RequestBody DocenteUnidadRequest request) {
        ApiResponse response = docenteUnidadService.updateDocenteUnidad(id, request);
        return ResponseEntity.ok(response);
    }
    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<DocenteUnidadDto> obtenerPorId(@PathVariable Integer id) {
        DocenteUnidadDto dto = docenteUnidadService.findById(id);
        return ResponseEntity.ok(dto);
    }




}
