package com.proyectoh.asignacion_de_horario.service;

import com.proyectoh.asignacion_de_horario.dto.request.DocenteUnidadRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.DocenteEntity;
import com.proyectoh.asignacion_de_horario.persistence.entity.DocenteUnidadEntity;
import com.proyectoh.asignacion_de_horario.persistence.entity.UnidadAcademicaEntity;
import com.proyectoh.asignacion_de_horario.persistence.repository.DocenteRepository;
import com.proyectoh.asignacion_de_horario.persistence.repository.DocenteUnidadRepository;
import com.proyectoh.asignacion_de_horario.persistence.repository.UnidadAcademicaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DocenteUnidadService {
    //Anotacion Autowired principal Docente Unidad
    @Autowired
    private DocenteUnidadRepository docenteUnidadRepository;
    //Buscar Id de Docente
    @Autowired
    private DocenteRepository docenteRepository;
    //Buscar Id de Unidad Academica
    @Autowired
    private UnidadAcademicaRepository unidadAcademicaRepository;
    //Vamos a controller antes de proseguir
    public ApiResponse createDocenteUnidad(DocenteUnidadRequest docenteUnidadRequest){
        try {
            //Buscamos el doncete Id
            DocenteEntity docenteEntity = docenteRepository.findById(docenteUnidadRequest.getDocenteId())
                    .orElseThrow(()->new ClassNotFoundException("Docente Unidad no encontrado"));
            //Buscamos la unidad academica Id
            UnidadAcademicaEntity unidadAcademicaEntity = unidadAcademicaRepository.findById(docenteUnidadRequest.getUnidadAcademicaId())
                    .orElseThrow(()->new ClassNotFoundException("Docente Unidad no encontrado"));
            DocenteUnidadEntity docenteUnidad = new DocenteUnidadEntity();
            docenteUnidad.setDocente(docenteEntity);
            docenteUnidad.setUnidadAcademica(unidadAcademicaEntity);
            //Guardar en la bse de datos
            docenteUnidadRepository.save(docenteUnidad);
            return new ApiResponse("Unidad registrado correctamente",docenteUnidad);

        }catch (Exception e){
            return new ApiResponse("No se registro la Unidad",null);
            //Terminado esto nos vamos al Controller para culminar
        }
    }
}
