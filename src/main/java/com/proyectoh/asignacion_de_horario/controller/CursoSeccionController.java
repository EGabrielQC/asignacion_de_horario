package com.proyectoh.asignacion_de_horario.controller;

import com.proyectoh.asignacion_de_horario.dto.CursoDto;
import com.proyectoh.asignacion_de_horario.dto.CursoSeccionDto;
import com.proyectoh.asignacion_de_horario.dto.request.CursoSeccionRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.CursoSeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursoSeccion")
public class CursoSeccionController {
    @Autowired
    private CursoSeccionService cursoSeccionService;
    //hacer el request antes de continuar
    @PostMapping
    public ResponseEntity<ApiResponse> createCursoSeccion(@RequestBody CursoSeccionRequest cursoSeccionRequest){
        ApiResponse apiResponse=cursoSeccionService.createCursoSeccion(cursoSeccionRequest);
        return ResponseEntity.ok(apiResponse);
    }
    //Para la Lista
    @GetMapping
    public ResponseEntity<List<CursoSeccionDto>> listarCursoSeccion() {
        return ResponseEntity.ok(cursoSeccionService.listAllCursoSecciones());
    }
    //Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminarCursoSeccion(@PathVariable Integer id) {
        ApiResponse response = cursoSeccionService.deleteCursoSeccion(id);
        return ResponseEntity.ok(response);
    }

    //Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizarCursoSeccion(@PathVariable Integer id, @RequestBody CursoSeccionRequest request) {
        ApiResponse response = cursoSeccionService.updateCursoSeccion(id, request);
        return ResponseEntity.ok(response);
    }

    //Buscar por Id
    @GetMapping("/{id}")
    public ResponseEntity<CursoSeccionDto> obtenerCursoSeccionPorId(@PathVariable Integer id) {
        CursoSeccionDto dto = cursoSeccionService.findCursoSeccionById(id);
        return ResponseEntity.ok(dto);
    }




}
