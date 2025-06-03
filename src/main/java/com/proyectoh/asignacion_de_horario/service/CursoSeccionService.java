package com.proyectoh.asignacion_de_horario.service;

import com.proyectoh.asignacion_de_horario.dto.CursoSeccionDto;
import com.proyectoh.asignacion_de_horario.dto.request.CursoSeccionRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.CursoEntity;
import com.proyectoh.asignacion_de_horario.persistence.entity.CursoSeccionEntity;
import com.proyectoh.asignacion_de_horario.persistence.entity.SeccionAcademicaEntity;
import com.proyectoh.asignacion_de_horario.persistence.repository.CursoRepository;
import com.proyectoh.asignacion_de_horario.persistence.repository.CursoSeccionRepository;
import com.proyectoh.asignacion_de_horario.persistence.repository.SeccionAcademicaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CursoSeccionService {
    // En caso de que tengamos relaciones principales hacemos antes de todo las inyecciones.
    //Hemos inyectado cursoSeccionRepository para guardar
    @Autowired
    private CursoSeccionRepository cursoSeccionRepository;
    //Hemos inyectado para buscar un curso por su Id
    @Autowired
    private CursoRepository cursoRepository;
    //Hemos inyectado para buscar una seccion por su Id
    @Autowired
    private SeccionAcademicaRepository seccionAcademicaRepository;

    //vamos a controller
    //Una función tiene un tipo de retorno, nombre, argumentos y el contenido dentro de las llaves
    public ApiResponse createCursoSeccion(CursoSeccionRequest cursoSeccionRequest){
        try {
            //Buscamos un curso por su Id
            CursoEntity cursoEntity = cursoRepository.findById(cursoSeccionRequest.getCursoId())
                    .orElseThrow(()->new ClassNotFoundException("Curso no encontrado"));

            //Programacion Funcional, Funcion Lamda => Una funcion dentro de una funcion.
            SeccionAcademicaEntity seccion = seccionAcademicaRepository.findById(cursoSeccionRequest.getSeccionAcademicaId())
                    .orElseThrow(()->new ClassNotFoundException("Seccion no encontrado"));
            //Crear un objeto -- creamos un objeto de la seccion entity
            CursoSeccionEntity cursoSeccionEntity = new CursoSeccionEntity();

            //Setear

            cursoSeccionEntity.setCurso(cursoEntity);
            cursoSeccionEntity.setSeccionAcademica(seccion);
            cursoSeccionEntity.setModo(cursoSeccionRequest.getModo());

            //Guardar en la base de datos
            cursoSeccionRepository.save(cursoSeccionEntity);
            return new ApiResponse("Curso seccion registrado correctamente", cursoSeccionEntity);
        } catch (Exception e){

            return new ApiResponse("Error al registrar curso seccion", null);
        }

    }
    //Listar
    public List<CursoSeccionDto> listAllCursoSecciones() {
        List<CursoSeccionEntity> cursoSecciones = cursoSeccionRepository.findAll();
        return cursoSecciones.stream().map(entity -> {
            CursoSeccionDto dto = new CursoSeccionDto();
            dto.setId(entity.getId());
            dto.setNombreCurso(entity.getCurso().getNombre());
            dto.setNombreSeccion(entity.getSeccionAcademica().getNombre());
            dto.setModo(entity.getModo());
            return dto;
        }).collect(Collectors.toList());
    }
    //Eliminar
    public ApiResponse deleteCursoSeccion(Integer id) {
        try {
            CursoSeccionEntity entity = cursoSeccionRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("CursoSeccion no encontrado con ID: " + id));

            cursoSeccionRepository.delete(entity);
            return new ApiResponse("CursoSeccion eliminado correctamente", null);
        } catch (Exception e) {
            log.error("Error al eliminar CursoSeccion", e);
            return new ApiResponse("Error al eliminar CursoSeccion", null);
        }
    }
    //Actualizar
    public ApiResponse updateCursoSeccion(Integer id, CursoSeccionRequest request) {
        try {
            CursoSeccionEntity entity = cursoSeccionRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("CursoSeccion no encontrado con ID: " + id));

            CursoEntity curso = cursoRepository.findById(request.getCursoId())
                    .orElseThrow(() -> new ClassNotFoundException("Curso no encontrado"));

            SeccionAcademicaEntity seccion = seccionAcademicaRepository.findById(request.getSeccionAcademicaId())
                    .orElseThrow(() -> new ClassNotFoundException("Sección no encontrada"));

            entity.setCurso(curso);
            entity.setSeccionAcademica(seccion);
            entity.setModo(request.getModo());

            cursoSeccionRepository.save(entity);
            return new ApiResponse("CursoSeccion actualizado correctamente", entity);

        } catch (Exception e) {
            log.error("Error al actualizar CursoSeccion", e);
            return new ApiResponse("Error al actualizar CursoSeccion", null);
        }
    }
    //Buscar por Id
    public CursoSeccionDto findCursoSeccionById(Integer id) {
        CursoSeccionEntity entity = cursoSeccionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("CursoSeccion no encontrado con ID: " + id));

        return new CursoSeccionDto(
                entity.getId(),
                entity.getCurso().getNombre(),
                entity.getSeccionAcademica().getNombre(),
                entity.getModo()
        );
    }

}
