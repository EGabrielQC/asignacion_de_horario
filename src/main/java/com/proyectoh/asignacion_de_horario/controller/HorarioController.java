package com.proyectoh.asignacion_de_horario.controller;


import com.proyectoh.asignacion_de_horario.dto.HorarioDto;
import com.proyectoh.asignacion_de_horario.dto.request.HorarioRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/horario")
public class HorarioController {
    //Llamamos a HorarioService
    @Autowired
    private HorarioService horarioService;
    //Vamos al request - service y culminamos aqui
    @PostMapping
    public ResponseEntity<ApiResponse> createHorario(@RequestBody HorarioRequest horarioRequest){
        ApiResponse apiResponse=horarioService.createHorario(horarioRequest);
        return ResponseEntity.ok(apiResponse);
    }

    // Endpoint para listar todos los horarios
    @GetMapping
    public ResponseEntity<List<HorarioDto>> listarHorarios() {
        List<HorarioDto> lista = horarioService.listarHorarios();
        return ResponseEntity.ok(lista);

    }
    // Eliminar horario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteHorario(@PathVariable Integer id) {
        ApiResponse response = horarioService.deleteHorario(id);
        return ResponseEntity.ok(response);
    }
    // Actualizar horario por ID
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateHorario(@PathVariable Integer id, @RequestBody HorarioRequest request) {
        ApiResponse response = horarioService.updateHorario(id, request);
        return ResponseEntity.ok(response);
    }
    // Buscar horario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getHorarioById(@PathVariable Integer id) {
        try {
            HorarioDto dto = horarioService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horario no encontrado");
        }
    }
}
