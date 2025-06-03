package com.proyectoh.asignacion_de_horario.controller;

import com.proyectoh.asignacion_de_horario.dto.AulaDto;
import com.proyectoh.asignacion_de_horario.dto.CursoDto;
import com.proyectoh.asignacion_de_horario.dto.request.AulaRequest;
import com.proyectoh.asignacion_de_horario.dto.request.CursoRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.AulaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController//Le estamos diciendo que va exponer recursos al cliente.
@RequestMapping("/aula")//Recibe parametros, Direccion de Recurso.
//@RequiredArgsConstructor//Inyeccion
public class AulaController{
    @Autowired
    private AulaService aulaService;
    //metodos Post(enviar datos), Get(consultar datos), Patch(Actualiza datos parcialmente), Delete, Put(Actualiza todos los atributos)
    //Resgistrar datos se usa en este metodo:
    @PostMapping
    //@RequestBody convierte clases a Formato Json
    public ResponseEntity<ApiResponse> createAula(@RequestBody AulaRequest aulaRequest){//<aqui entra un objeto>

        ApiResponse apiResponse=aulaService.createAula(aulaRequest);
        return ResponseEntity.ok(apiResponse);
    }
    //Listar Aulas
    @GetMapping
    public ResponseEntity<List<AulaDto>> listarAulas() {
        List<AulaDto> aulas = aulaService.listAllAulas();
        return ResponseEntity.ok(aulas);
    }
    //Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminarAula(@PathVariable Integer id) {
        ApiResponse response = aulaService.eliminarAula(id);
        if (response.getData() == null) {
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }
    //Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizarAula(@PathVariable Integer id, @RequestBody AulaRequest aulaRequest) {
        ApiResponse response = aulaService.actualizarAula(id, aulaRequest);
        if (response.getData() == null) {
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }
    //Buscar Id
    @GetMapping("/{id}")
    public ResponseEntity<AulaDto> buscarAulaPorId(@PathVariable Integer id) {
        Optional<AulaDto> optionalAula = aulaService.buscarAulaPorId(id);
        return optionalAula
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
