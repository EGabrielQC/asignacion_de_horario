package com.proyectoh.asignacion_de_horario.service;
import com.proyectoh.asignacion_de_horario.dto.AulaDto;
import com.proyectoh.asignacion_de_horario.dto.request.AulaRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.AulaEntity;
import com.proyectoh.asignacion_de_horario.persistence.repository.AulaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service Se encarga de la logica de gestionar aulas. eliminar actualizar y editar.
//Metodo
//Modularidad -->
//Inyeccion de Dependencias, Anotaciones metodos get y set, mas recomendable los constructores
//Apyrest --
//Peticion -- responde con Response -- registra que el aula a sido registrada.
@Service
//@RequiredArgsConstructor//Con esto estamos inyectando, aula repository se puede usar dentro de service.
@Slf4j//habilitar automáticamente un logger --- registrador de eventos
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
           log.error("Error al crear Aula{}", e.getMessage());
           return new ApiResponse("Error al crear el Aula"+ e.getMessage(), null);
       }
    }
    //Metodo para listar Aulas
    //Este metodo retorna una lISTA Colección
    public List<AulaDto> listAllAulas(){
        List<AulaEntity> aulas=aulaRepository.findAll();
        //Programacion funcional
        return aulas.stream()
                .map(aulaEntity -> {
                    AulaDto dto=new AulaDto();
                    dto.setId(aulaEntity.getId());
                    dto.setNombre(aulaEntity.getNombre());
                    dto.setCapacidad(aulaEntity.getCapacidad());
                    dto.setTipo(aulaEntity.getTipo());
                    return dto;
                }) .toList();
    }
    //Método para Eliminar
    public ApiResponse eliminarAula(Integer id) {
        try {
            if (!aulaRepository.existsById(id)) {
                return new ApiResponse("Aula no encontrada con ID: " + id, null);
            }
            aulaRepository.deleteById(id);
            log.info("Aula con ID {} eliminada correctamente", id);
            return new ApiResponse("Aula eliminada correctamente", null);
        } catch (Exception e) {
            log.error("Error al eliminar aula {}", e.getMessage());
            return new ApiResponse("Error al eliminar aula: " + e.getMessage(), null);
        }
    }
        //Actualizar Métodos
        public ApiResponse actualizarAula(Integer id, AulaRequest aulaRequest) {
            try {
                AulaEntity aulaEntity = aulaRepository.findById(id).orElse(null);
                if (aulaEntity == null) {
                    return new ApiResponse("Aula no encontrada con ID: " + id, null);
                }

                aulaEntity.setNombre(aulaRequest.getNombre());
                aulaEntity.setCapacidad(aulaRequest.getCapacidad());
                aulaEntity.setTipo(aulaRequest.getTipo());

                aulaRepository.save(aulaEntity);
                log.info("Aula con ID {} actualizada correctamente", id);
                return new ApiResponse("Aula actualizada correctamente", aulaEntity);
            } catch (Exception e) {
                log.error("Error al actualizar aula {}", e.getMessage());
                return new ApiResponse("Error al actualizar aula: " + e.getMessage(), null);
            }
        }
        //Buscar Aula por Id con Optional<Aula Dto>
        public Optional<AulaDto> buscarAulaPorId(Integer id) {
            try {
                return aulaRepository.findById(id)
                        .map(aulaEntity -> {
                            AulaDto dto = new AulaDto();
                            dto.setId(aulaEntity.getId());
                            dto.setNombre(aulaEntity.getNombre());
                            dto.setCapacidad(aulaEntity.getCapacidad());
                            dto.setTipo(aulaEntity.getTipo());
                            return dto;
                        });
            } catch (Exception e) {
                log.error("Error al buscar aula por ID {}: {}", id, e.getMessage());
                return Optional.empty();
            }
        }


}




