package com.proyectoh.asignacion_de_horario.service;

import com.proyectoh.asignacion_de_horario.dto.request.DisponibilidadDocenteRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.DisponibilidadDocenteEntity;
import com.proyectoh.asignacion_de_horario.persistence.entity.DocenteEntity;
import com.proyectoh.asignacion_de_horario.persistence.repository.DisponibilidadDocenteRepository;
import com.proyectoh.asignacion_de_horario.persistence.repository.DocenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DisponibilidadDocenteService {
    @Autowired
    private DisponibilidadDocenteRepository disponibilidadDocenteRepository;
    @Autowired
    private DocenteRepository docenteRepository;
    //Vamos a Controller
    public ApiResponse createDisponibilidadDocente(DisponibilidadDocenteRequest disponibilidadDocenteRequest){
        try {
            //Buscamos el doncenteId
            DocenteEntity docenteEntity = docenteRepository.findById(disponibilidadDocenteRequest.getDocenteId())
                    .orElseThrow(()->new ClassNotFoundException("Docente no encontrado"));
            DisponibilidadDocenteEntity disponibilidad = new DisponibilidadDocenteEntity();
            //Seteamos
            disponibilidad.setDocente(docenteEntity);
            disponibilidad.setDiaSemana(disponibilidadDocenteRequest.getDiaSemana());
            disponibilidad.setHoraInicio(disponibilidadDocenteRequest.getHoraInicio());
            disponibilidad.setHoraFin(disponibilidadDocenteRequest.getHoraFin());
            disponibilidad.setTipo(disponibilidadDocenteRequest.getTipo());
            //Guardar en la base de datos
            disponibilidadDocenteRepository.save(disponibilidad);
            return new ApiResponse("Disponiblidad de Docente registrado correctamente", disponibilidad);
        }catch (Exception e){
            return new ApiResponse("Error al restrar disponibilidad de docente", null);
        }
    }
}
