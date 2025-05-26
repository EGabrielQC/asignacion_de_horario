package com.proyectoh.asignacion_de_horario.service;

import com.proyectoh.asignacion_de_horario.dto.request.CursoRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.CursoEntity;
import com.proyectoh.asignacion_de_horario.persistence.repository.CursoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CursoService {
    @Autowired
    //Estoy creando una clase de servicio (CursoService)
    // donde Spring me inyecta automáticamente un CursoRepository para acceder a la base de datos
    private CursoRepository cursoRepository;
    //Antes de continuar con el ApiResponse vamos a controller
    public ApiResponse createCurso(CursoRequest cursoRequest){
        try {
            CursoEntity cursoEntity = new CursoEntity();
            cursoEntity.setNombre(cursoRequest.getNombre());
            cursoEntity.setHorasSemanales(cursoRequest.getHorasSemanales());
            cursoEntity.setTipo(cursoRequest.getTipo());
            cursoRepository.save(cursoEntity);
            log.info("Curso guardado correctamente");
            return new ApiResponse("Curso creado correctamente", cursoEntity);
        }catch (Exception e){
            log.error("Error al crear curso {}", e.getMessage());
            return new ApiResponse("Error al  crear Curso"+ e.getMessage(), null);
        }
    }
}