package com.proyectoh.asignacion_de_horario.service;

import com.proyectoh.asignacion_de_horario.dto.request.UnidadAcademicaRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.UnidadAcademicaEntity;
import com.proyectoh.asignacion_de_horario.persistence.repository.UnidadAcademicaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UnidadAcademicaService {
    @Autowired
    private UnidadAcademicaRepository unidadAcademicaRepository;
    //Controller
    public ApiResponse createUnidadAcademica(UnidadAcademicaRequest unidadAcademicaRequest){
        try{
            UnidadAcademicaEntity unidadAcademica = new UnidadAcademicaEntity();
            unidadAcademica.setNombre(unidadAcademicaRequest.getNombre());
            //Guardar en la base de datos
            unidadAcademicaRepository.save(unidadAcademica);
            return new ApiResponse("unidad registrada corecctamente",unidadAcademica);
        }catch (Exception e){
            return new ApiResponse("unidad no registrada",null);
            //para finalizar controller
        }
    }
}
