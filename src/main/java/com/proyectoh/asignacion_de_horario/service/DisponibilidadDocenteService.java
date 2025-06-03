package com.proyectoh.asignacion_de_horario.service;

import com.proyectoh.asignacion_de_horario.dto.DisponibilidadDocenteDto;
import com.proyectoh.asignacion_de_horario.dto.request.DisponibilidadDocenteRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.DisponibilidadDocenteEntity;
import com.proyectoh.asignacion_de_horario.persistence.entity.DocenteEntity;
import com.proyectoh.asignacion_de_horario.persistence.repository.DisponibilidadDocenteRepository;
import com.proyectoh.asignacion_de_horario.persistence.repository.DocenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
    //Listar Disponibilidades
    public List<DisponibilidadDocenteDto> listarDisponibilidades() {
        List<DisponibilidadDocenteEntity> disponibilidades = disponibilidadDocenteRepository.findAll();

        return disponibilidades.stream().map(disponibilidad -> {
            DisponibilidadDocenteDto dto = new DisponibilidadDocenteDto();
            dto.setId(disponibilidad.getId());
            dto.setDocenteNombre(disponibilidad.getDocente().getNombre()); // Suponiendo que DocenteEntity tiene getNombre()
            dto.setDiaSemana(disponibilidad.getDiaSemana());
            dto.setHoraInicio(disponibilidad.getHoraInicio());
            dto.setHoraFin(disponibilidad.getHoraFin());
            dto.setTipo(disponibilidad.getTipo());
            return dto;
        }).collect(Collectors.toList());
    }
    //Eliminar
    public ApiResponse deleteDisponibilidadDocente(Integer id) {
        try {
            DisponibilidadDocenteEntity disponibilidad = disponibilidadDocenteRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Disponibilidad no encontrada con ID: " + id));

            disponibilidadDocenteRepository.delete(disponibilidad);
            return new ApiResponse("Disponibilidad eliminada correctamente", null);
        } catch (Exception e) {
            log.error("Error al eliminar disponibilidad", e);
            return new ApiResponse("Error al eliminar disponibilidad", null);
        }
    }

    //Actualizar
    public ApiResponse updateDisponibilidadDocente(Integer id, DisponibilidadDocenteRequest request) {
        try {
            DisponibilidadDocenteEntity disponibilidad = disponibilidadDocenteRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Disponibilidad no encontrada con ID: " + id));

            DocenteEntity docente = docenteRepository.findById(request.getDocenteId())
                    .orElseThrow(() -> new ClassNotFoundException("Docente no encontrado"));

            disponibilidad.setDocente(docente);
            disponibilidad.setDiaSemana(request.getDiaSemana());
            disponibilidad.setHoraInicio(request.getHoraInicio());
            disponibilidad.setHoraFin(request.getHoraFin());
            disponibilidad.setTipo(request.getTipo());

            disponibilidadDocenteRepository.save(disponibilidad);
            return new ApiResponse("Disponibilidad actualizada correctamente", disponibilidad);
        } catch (Exception e) {
            log.error("Error al actualizar disponibilidad", e);
            return new ApiResponse("Error al actualizar disponibilidad", null);
        }
    }

    //Buscar por Id
    public DisponibilidadDocenteDto findById(Integer id) {
        DisponibilidadDocenteEntity disponibilidad = disponibilidadDocenteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Disponibilidad no encontrada con ID: " + id));

        return new DisponibilidadDocenteDto(
                disponibilidad.getId(),
                disponibilidad.getDocente().getNombre(),
                disponibilidad.getDiaSemana(),
                disponibilidad.getHoraInicio(),
                disponibilidad.getHoraFin(),
                disponibilidad.getTipo()
        );
    }

}
