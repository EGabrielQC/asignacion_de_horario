package com.proyectoh.asignacion_de_horario.service;


import com.proyectoh.asignacion_de_horario.dto.request.PeriodoAcademicoRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.PeriodoAcademicoEntity;
import com.proyectoh.asignacion_de_horario.persistence.repository.PeriodoAcademicoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PeriodoAcademicoService {
    @Autowired
    private PeriodoAcademicoRepository periodoAcademicoRepository;
    //Vamos al controller
    public ApiResponse createPeriodoAcademico(PeriodoAcademicoRequest periodoAcademicoRequest){
        try {
            PeriodoAcademicoEntity periodoAcademico = new PeriodoAcademicoEntity();
            periodoAcademico.setNombre(periodoAcademicoRequest.getNombre());
            periodoAcademico.setFechaInicio(periodoAcademicoRequest.getFechaInicio());
            periodoAcademico.setFechaFin(periodoAcademicoRequest.getFechaFin());
            //Guardar en la base de datos
            periodoAcademicoRepository.save(periodoAcademico);
            return new ApiResponse("Periodo registrado correctamente",periodoAcademico);
        }catch (Exception e){
            return new ApiResponse("Periodo no registrado",null);
            //Ir a controller para finalizar
        }
    }
}
