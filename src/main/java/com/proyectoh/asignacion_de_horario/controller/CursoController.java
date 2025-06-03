package com.proyectoh.asignacion_de_horario.controller;


import com.proyectoh.asignacion_de_horario.dto.CursoDto;

import com.proyectoh.asignacion_de_horario.dto.request.CursoRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    //Hacer antes el Request
    @PostMapping
    public ResponseEntity<ApiResponse> createCurso(@RequestBody CursoRequest cursoRequest) {

        ApiResponse apiResponse = cursoService.createCurso(cursoRequest);
        return ResponseEntity.ok(apiResponse);
    }

    //Metodo GetMappin
    @GetMapping
    public ResponseEntity<List<CursoDto>> listarCursos() {
        return ResponseEntity.ok(cursoService.listAllCursos());
    }

    //Eliminar curso
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminarCurso(@PathVariable Integer id) {
        ApiResponse response = cursoService.eliminarCurso(id);
        return ResponseEntity.ok(response);
    }

    //Actualizar curso
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizarCurso(@PathVariable Integer id, @RequestBody CursoRequest cursoRequest) {
        ApiResponse response = cursoService.actualizarCurso(id, cursoRequest);
        return ResponseEntity.ok(response);
    }

    //Buscar curso por ID y retornar directamente Optional<CursoDto>
    @GetMapping("/{id}")
    public ResponseEntity<CursoDto> buscarCursoPorId(@PathVariable Integer id) {
        Optional<CursoDto> optionalCurso = cursoService.buscarCursoPorId(id);
        return optionalCurso
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}