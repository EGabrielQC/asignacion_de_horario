package com.proyectoh.asignacion_de_horario.service;


import com.proyectoh.asignacion_de_horario.dto.SeccionAcademicaDto;
import com.proyectoh.asignacion_de_horario.dto.request.SeccionAcademicaRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.*;
import com.proyectoh.asignacion_de_horario.persistence.repository.PeriodoAcademicoRepository;
import com.proyectoh.asignacion_de_horario.persistence.repository.SeccionAcademicaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
            public List<SeccionAcademicaDto> listarSeccionesAcademicas() {
                List<SeccionAcademicaEntity> lista = seccionAcademicaRepository.findAll();

                return lista.stream().map(seccion -> {
                    SeccionAcademicaDto dto = new SeccionAcademicaDto();
                    dto.setId(seccion.getId());
                    dto.setNombre(seccion.getNombre());

                    // Obtener nombre del periodo académico asociado
                    if (seccion.getPeriodoAcademico() != null) {
                        dto.setPeriodoAcademico(seccion.getPeriodoAcademico().getNombre());
                    } else {
                        dto.setPeriodoAcademico("Sin periodo asignado");
                    }

                    return dto;
                }).collect(Collectors.toList());
            }

    // Eliminar sección académica
    public ApiResponse deleteSeccionAcademica(Integer id) {
        try {
            SeccionAcademicaEntity seccion = seccionAcademicaRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Sección no encontrada con ID: " + id));

            seccionAcademicaRepository.delete(seccion);

            return new ApiResponse("Sección eliminada correctamente", null);
        } catch (Exception e) {
            return new ApiResponse("Error al eliminar sección", null);
        }
    }
    // Actualizar sección académica
    public ApiResponse updateSeccionAcademica(Integer id, SeccionAcademicaRequest request) {
        try {
            SeccionAcademicaEntity seccion = seccionAcademicaRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Sección no encontrada con ID: " + id));

            PeriodoAcademicoEntity periodo = periodoAcademicoRepository.findById(request.getPeridoAcademicoId())
                    .orElseThrow(() -> new ClassNotFoundException("Periodo no encontrado"));

            seccion.setNombre(request.getNombre());
            seccion.setPeriodoAcademico(periodo);

            seccionAcademicaRepository.save(seccion);

            return new ApiResponse("Sección actualizada correctamente", seccion);
        } catch (Exception e) {
            return new ApiResponse("Error al actualizar sección", null);
        }
    }
    // Buscar por ID
    public SeccionAcademicaDto findById(Integer id) {
        SeccionAcademicaEntity seccion = seccionAcademicaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Sección no encontrada con ID: " + id));

        SeccionAcademicaDto dto = new SeccionAcademicaDto();
        dto.setId(seccion.getId());
        dto.setNombre(seccion.getNombre());

        if (seccion.getPeriodoAcademico() != null) {
            dto.setPeriodoAcademico(seccion.getPeriodoAcademico().getNombre());
        } else {
            dto.setPeriodoAcademico("Sin periodo asignado");
        }

        return dto;
    }

}
