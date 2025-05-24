package com.proyectoh.asignacion_de_horario.service;
import com.proyectoh.asignacion_de_horario.dto.request.AulaRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.AulaEntity;
import com.proyectoh.asignacion_de_horario.persistence.repository.AulaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// @Service Se encarga de la logica de gestionar aulas. eliminar actualizar y editar.
//Metodo
//Modularidad -->
//Inyeccion de Dependencias, Anotaciones metodos get y set, mas recomendable los constructores
//Apyrest --
//Peticion -- responde con Response -- registra que el aula a sido registrada.
@Service
//@RequiredArgsConstructor//Con esto estamos inyectando, aula repository se puede usar dentro de service.
@Slf4j
public class AulaService {
    @Autowired
    private AulaRepository aulaRepository;
    //Metodo que retorna Objeto en este caso un Api response --
    public ApiResponse createAula (AulaRequest aulaRequest){
    //Lógica para guardar un usuario
    //Instanacia --> creamos un objeto de la clase AulaEntity
       try {
           AulaEntity aulaEntity = new AulaEntity();
           aulaEntity.setNombre(aulaRequest.getNombre());
           aulaEntity.setCapacidad(aulaRequest.getCapacidad());
           aulaEntity.setTipo(aulaRequest.getTipo());

           aulaRepository.save(aulaEntity);
           log.info("aula guardado correctamente");
           return new ApiResponse("Aula creado correctamente", aulaEntity);
       }catch (Exception e){
           log.error("Error al crear Aula"+ e.getMessage());
           return new ApiResponse("Error al crear el Aula"+ e.getMessage(), null);
       }
    }

}

