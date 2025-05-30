package com.proyectoh.asignacion_de_horario.service;


import com.proyectoh.asignacion_de_horario.dto.request.SeccionAcademicaRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.*;
import com.proyectoh.asignacion_de_horario.persistence.repository.PeriodoAcademicoRepository;
import com.proyectoh.asignacion_de_horario.persistence.repository.SeccionAcademicaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SeccionAcademicaService {
    //Anotacion principal
    @Autowired
    private SeccionAcademicaRepository seccionAcademicaRepository;
    //Para buscar Id de Periodo Academico
    @Autowired
    private PeriodoAcademicoRepository periodoAcademicoRepository;
    //Vamos al controller
    public ApiResponse createSeccionAcademica(SeccionAcademicaRequest seccionAcademicaRequest){
        try {
            //Buscamos el Periodo academico Id
            PeriodoAcademicoEntity periodoAcademico = periodoAcademicoRepository.findById(seccionAcademicaRequest.getPeridoAcademicoId())
                    .orElseThrow(()->new ClassNotFoundException("periodo no encontrado"));
            SeccionAcademicaEntity seccionAcademica = new SeccionAcademicaEntity();
            //Setear
            seccionAcademica.setNombre(seccionAcademicaRequest.getNombre());
            seccionAcademica.setPeriodoAcademico(periodoAcademico);
            //Guardar base de datos
            seccionAcademicaRepository.save(seccionAcademica);
            return new ApiResponse("Seccion registrado correctamente",seccionAcademica);
        }catch (Exception e){
            return new ApiResponse("No se registro la seccion",null);

            //Culminar en Controller

        }
    }
}
