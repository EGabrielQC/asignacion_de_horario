package com.proyectoh.asignacion_de_horario.controller;

import com.proyectoh.asignacion_de_horario.dto.DisponibilidadDocenteDto;
import com.proyectoh.asignacion_de_horario.dto.request.DisponibilidadDocenteRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.DisponibilidadDocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disponibilidad")
public class DisponibilidadDocenteController {
    @Autowired
    private DisponibilidadDocenteService disponibilidadDocenteService;
    //Hacer el request antes de proseguir.
    @PostMapping
    public ResponseEntity<ApiResponse> createDisponibilidadDocente(@RequestBody DisponibilidadDocenteRequest disponibilidadDocenteRequest){
        ApiResponse apiResponse=disponibilidadDocenteService.createDisponibilidadDocente(disponibilidadDocenteRequest);
        return ResponseEntity.ok(apiResponse);
    }
    //GET: Listar todas las disponibilidades de docentes
    @GetMapping
    public ResponseEntity<List<DisponibilidadDocenteDto>> listarDisponibilidades() {
        List<DisponibilidadDocenteDto> lista = disponibilidadDocenteService.listarDisponibilidades();
        return ResponseEntity.ok(lista);
    }
    //Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminarDisponibilidad(@PathVariable Integer id) {
        ApiResponse response = disponibilidadDocenteService.deleteDisponibilidadDocente(id);
        return ResponseEntity.ok(response);
    }

    //Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizarDisponibilidad(
            @PathVariable Integer id,
            @RequestBody DisponibilidadDocenteRequest request) {
        ApiResponse response = disponibilidadDocenteService.updateDisponibilidadDocente(id, request);
        return ResponseEntity.ok(response);
    }

    //Buscar por Id
    @GetMapping("/{id}")
    public ResponseEntity<DisponibilidadDocenteDto> obtenerDisponibilidadPorId(@PathVariable Integer id) {
        DisponibilidadDocenteDto dto = disponibilidadDocenteService.findById(id);
        return ResponseEntity.ok(dto);
    }

}
