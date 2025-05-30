package com.proyectoh.asignacion_de_horario.service;

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

}
