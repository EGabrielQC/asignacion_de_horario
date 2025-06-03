package com.proyectoh.asignacion_de_horario.service;


import com.proyectoh.asignacion_de_horario.dto.PeriodoAcademicaDto;
import com.proyectoh.asignacion_de_horario.dto.request.PeriodoAcademicoRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.PeriodoAcademicoEntity;
import com.proyectoh.asignacion_de_horario.persistence.repository.PeriodoAcademicoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
    //Listar
    public List<PeriodoAcademicaDto> listarPeriodos() {
        List<PeriodoAcademicoEntity> lista = periodoAcademicoRepository.findAll();

        return lista.stream().map(periodo -> {
            PeriodoAcademicaDto dto = new PeriodoAcademicaDto();
            dto.setId(periodo.getId());
            dto.setNombre(periodo.getNombre());
            dto.setFechaInicio(periodo.getFechaInicio());
            dto.setFechaFin(periodo.getFechaFin());
            return dto;
        }).collect(Collectors.toList());
    }


    // Eliminar
    public ApiResponse deletePeriodo(Integer id) {
        try {
            PeriodoAcademicoEntity periodo = periodoAcademicoRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Periodo académico no encontrado"));

            periodoAcademicoRepository.delete(periodo);
            return new ApiResponse("Periodo eliminado correctamente", null);
        } catch (Exception e) {
            return new ApiResponse("Error al eliminar el periodo", null);
        }
    }
    // Actualizar
    public ApiResponse updatePeriodo(Integer id, PeriodoAcademicoRequest request) {
        try {
            PeriodoAcademicoEntity periodo = periodoAcademicoRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Periodo académico no encontrado"));

            periodo.setNombre(request.getNombre());
            periodo.setFechaInicio(request.getFechaInicio());
            periodo.setFechaFin(request.getFechaFin());

            periodoAcademicoRepository.save(periodo);
            return new ApiResponse("Periodo actualizado correctamente", periodo);
        } catch (Exception e) {
            return new ApiResponse("Error al actualizar el periodo", null);
        }
    }

    // Buscar por ID
    public PeriodoAcademicaDto findById(Integer id) {
        PeriodoAcademicoEntity periodo = periodoAcademicoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Periodo académico no encontrado"));
        return new PeriodoAcademicaDto(
                periodo.getId(),
                periodo.getNombre(),
                periodo.getFechaInicio(),
                periodo.getFechaFin()
        );
    }
}
