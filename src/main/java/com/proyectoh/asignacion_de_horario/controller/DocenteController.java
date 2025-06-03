package com.proyectoh.asignacion_de_horario.controller;

import com.proyectoh.asignacion_de_horario.dto.DocenteDto;
import com.proyectoh.asignacion_de_horario.dto.request.DocenteRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    // Listar docentes
    @GetMapping
    public ResponseEntity<List<DocenteDto>> listarDocentes() {
        List<DocenteDto> lista = docenteService.listarDocentes();
        return ResponseEntity.ok(lista);
    }
    // Eliminar docente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminarDocente(@PathVariable Integer id) {
        ApiResponse response = docenteService.deleteDocente(id);
        return ResponseEntity.ok(response);
    }
    // Actualizar docente por ID
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizarDocente(
            @PathVariable Integer id,
            @RequestBody DocenteRequest docenteRequest) {
        ApiResponse response = docenteService.updateDocente(id, docenteRequest);
        return ResponseEntity.ok(response);
    }
    // Obtener docente por ID
    @GetMapping("/{id}")
    public ResponseEntity<DocenteDto> obtenerDocentePorId(@PathVariable Integer id) {
        DocenteDto dto = docenteService.findById(id);
        return ResponseEntity.ok(dto);
    }


}

