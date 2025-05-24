package com.proyectoh.asignacion_de_horario.controller;

import com.proyectoh.asignacion_de_horario.dto.request.AulaRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.service.AulaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
