package com.proyectoh.asignacion_de_horario.controller;

import com.proyectoh.asignacion_de_horario.dto.HorarioBloqueDto;
import com.proyectoh.asignacion_de_horario.dto.request.HorarioBloqueRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.HorarioBloqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horarioBloque")
public class HorarioBloqueController {
    @Autowired
    HorarioBloqueService horarioBloqueService;
    //Seguir con el request y luego service para proseguir
    @PostMapping
    public ResponseEntity<ApiResponse> createBloqueHorario(@RequestBody HorarioBloqueRequest horarioBloqueRequest){
        ApiResponse apiResponse=horarioBloqueService.createHorarioBloque(horarioBloqueRequest);
        return ResponseEntity.ok(apiResponse);
    }
    // Listar todos los bloques de horario
    @GetMapping
    public ResponseEntity<List<HorarioBloqueDto>> listarBloquesHorario() {
        List<HorarioBloqueDto> lista = horarioBloqueService.listarHorarioBloques();
        return ResponseEntity.ok(lista);
    }
    // Eliminar bloque
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBloqueHorario(@PathVariable Integer id) {
        ApiResponse apiResponse = horarioBloqueService.deleteHorarioBloque(id);
        return ResponseEntity.ok(apiResponse);
    }
    // Actualizar bloque
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateBloqueHorario(
            @PathVariable Integer id,
            @RequestBody HorarioBloqueRequest request
    ) {
        ApiResponse apiResponse = horarioBloqueService.updateHorarioBloque(id, request);
        return ResponseEntity.ok(apiResponse);
    }

    // Buscar bloque por ID
    @GetMapping("/{id}")
    public ResponseEntity<HorarioBloqueDto> getBloquePorId(@PathVariable Integer id) {
        HorarioBloqueDto dto = horarioBloqueService.findById(id);
        return ResponseEntity.ok(dto);
    }



}
