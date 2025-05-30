package com.proyectoh.asignacion_de_horario.service;

import com.proyectoh.asignacion_de_horario.dto.request.HorarioRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.*;
import com.proyectoh.asignacion_de_horario.persistence.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HorarioService {
    //Anotacion Autowired Principal
    @Autowired
    private HorarioRepository horarioRepository;
    //Buscar Id de docente
    @Autowired
    private DocenteRepository docenteRepository;
    //Buscar Id seccion
    @Autowired
    private CursoSeccionRepository seccionRepository;
    //Buscar Id aula
    @Autowired
    private AulaRepository aulaRepository;
    //Buscar bloque
    @Autowired
    private HorarioBloqueRepository bloqueRepository;

    //Vamos a controller antes de proseguir
public ApiResponse createHorario(HorarioRequest horarioRequest){
    try {
    //Buscamos el docente Id
        DocenteEntity docenteEntity = docenteRepository.findById(horarioRequest.getDocenteId())
                .orElseThrow(()->new ClassNotFoundException("Docente Unidad no encontrado"));
        CursoSeccionEntity cursoSeccion = seccionRepository.findById(horarioRequest.getCursoSeccionId())
                .orElseThrow(()->new ClassNotFoundException("Curso seccion no encontrado"));
        AulaEntity aula = aulaRepository.findById(horarioRequest.getAulaId())
                .orElseThrow(()->new ClassNotFoundException("Aula no encontrada"));
        HorarioBloqueEntity horarioBloque = bloqueRepository.findById(horarioRequest.getBloqueHorarioId())
                .orElseThrow(()->new ClassNotFoundException("Bloque no encontrado"));
        HorarioEntity horarioEntity = new HorarioEntity();
        //Setear
        horarioEntity.setDocente(docenteEntity);
        horarioEntity.setCursoSeccion(cursoSeccion);
        horarioEntity.setAula(aula);
        horarioEntity.setBloqueHorario(horarioBloque);

        //Guardar base de datos
        horarioRepository.save(horarioEntity);
        return new ApiResponse("Horario registrado correctamente",horarioEntity);
    }catch (Exception e){
        return new ApiResponse("No se registro el horario",null);

    }
}


}
