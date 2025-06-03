package com.proyectoh.asignacion_de_horario.service;


import com.proyectoh.asignacion_de_horario.dto.CursoDto;
import com.proyectoh.asignacion_de_horario.dto.request.CursoRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;

import com.proyectoh.asignacion_de_horario.persistence.entity.CursoEntity;
import com.proyectoh.asignacion_de_horario.persistence.repository.CursoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    //Este metodo retorna una lISTA Colección
    @Transactional
    public List<CursoDto> listAllCursos(){
        List<CursoEntity> cursos=cursoRepository.findAll();
        //Programacion funcional
        return cursos.stream()
                .map(cursoEntity -> {
                    CursoDto dto=new CursoDto();
                    dto.setId(cursoEntity.getId());
                    dto.setNombre(cursoEntity.getNombre());
                    dto.setHorasSemanales(cursoEntity.getHorasSemanales());
                    dto.setTipo(cursoEntity.getTipo());

                    dto.setNombreCurso(cursoEntity.getUnidadAcademica()!=null
                    ?cursoEntity.getUnidadAcademica().getNombre()
                            :null);
                    return dto;
                }) .toList();
    }
    // Método para eliminar un curso por ID
    public ApiResponse eliminarCurso(Integer id) {
        try {
            if (!cursoRepository.existsById(id)) {
                return new ApiResponse("Curso no encontrado con ID: " + id, null);
            }
            cursoRepository.deleteById(id);
            log.info("Curso eliminado con ID: {}", id);
            return new ApiResponse("Curso eliminado correctamente", null);
        } catch (Exception e) {
            log.error("Error al eliminar curso {}", e.getMessage());
            return new ApiResponse("Error al eliminar curso: " + e.getMessage(), null);
        }
    }

    // Método para actualizar un curso
    @Transactional
    public ApiResponse actualizarCurso(Integer id, CursoRequest cursoRequest) {
        try {
            CursoEntity cursoEntity = cursoRepository.findById(id).orElse(null);
            if (cursoEntity == null) {
                return new ApiResponse("Curso no encontrado con ID: " + id, null);
            }

            cursoEntity.setNombre(cursoRequest.getNombre());
            cursoEntity.setHorasSemanales(cursoRequest.getHorasSemanales());
            cursoEntity.setTipo(cursoRequest.getTipo());

            cursoRepository.save(cursoEntity);
            log.info("Curso actualizado correctamente con ID: {}", id);
            return new ApiResponse("Curso actualizado correctamente", cursoEntity);
        } catch (Exception e) {
            log.error("Error al actualizar curso {}", e.getMessage());
            return new ApiResponse("Error al actualizar curso: " + e.getMessage(), null);
        }
    }

    // Método para buscar un curso por ID
    public Optional<CursoDto> buscarCursoPorId(Integer id) {
        try {
            return cursoRepository.findById(id)
                    .map(cursoEntity -> {
                        CursoDto dto = new CursoDto();
                        dto.setId(cursoEntity.getId());
                        dto.setNombre(cursoEntity.getNombre());
                        dto.setHorasSemanales(cursoEntity.getHorasSemanales());
                        dto.setTipo(cursoEntity.getTipo());
                        dto.setNombreCurso(
                                cursoEntity.getUnidadAcademica() != null
                                        ? cursoEntity.getUnidadAcademica().getNombre()
                                        : null
                        );
                        return dto;
                    });
        } catch (Exception e) {
            log.error("Error al buscar curso por ID {}: {}", id, e.getMessage());
            return Optional.empty(); // En caso de error también se retorna vacío
        }
    }


}