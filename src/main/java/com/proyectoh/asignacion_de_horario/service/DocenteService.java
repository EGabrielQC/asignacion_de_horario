package com.proyectoh.asignacion_de_horario.service;

import com.proyectoh.asignacion_de_horario.dto.request.DocenteRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.DocenteEntity;
import com.proyectoh.asignacion_de_horario.persistence.repository.DocenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DocenteService {
    //Anotación Autowired
    @Autowired
    private DocenteRepository docenteRepository;
    //Vamos a controller
    public ApiResponse createDocente(DocenteRequest docenteRequest){
        try {
            DocenteEntity docenteEntity = new DocenteEntity();

            docenteEntity.setNombre(docenteRequest.getNombre());
            docenteEntity.setApellido(docenteRequest.getApellido());
            docenteEntity.setCorreo(docenteRequest.getCorreo());
            docenteEntity.setHoraContratadas(docenteRequest.getHoraContratadas());
            docenteEntity.setMaxHorasPorDia(docenteRequest.getMaxHorasPorDia());
            //Guardar en la Base de datos
            docenteRepository.save(docenteEntity);
            return new ApiResponse("Docente registrado correctamente", docenteEntity);
        } catch (Exception e){
            return new ApiResponse("Error de registro de docente", null);
        }
    }
}
