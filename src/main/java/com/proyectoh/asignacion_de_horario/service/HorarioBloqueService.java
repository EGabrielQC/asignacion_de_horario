package com.proyectoh.asignacion_de_horario.service;


import com.proyectoh.asignacion_de_horario.dto.HorarioBloqueDto;
import com.proyectoh.asignacion_de_horario.dto.request.HorarioBloqueRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.HorarioBloqueEntity;
import com.proyectoh.asignacion_de_horario.persistence.repository.HorarioBloqueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HorarioBloqueService {
    //Anotacion Autowired principal Docente Unidad del repository
    @Autowired
    private HorarioBloqueRepository horarioBloqueRepository;
    //Vamos a controller-Request para proseguir
    public ApiResponse createHorarioBloque(HorarioBloqueRequest horarioBloqueRequest){
        try {
            HorarioBloqueEntity horarioBloque = new HorarioBloqueEntity();
            horarioBloque.setDiaSemana(horarioBloqueRequest.getDiaSemana());
            horarioBloque.setHoraInicio(horarioBloqueRequest.getHoraInicio());
            horarioBloque.setHoraFin(horarioBloqueRequest.getHoraFin());
            horarioBloque.setTurno(horarioBloqueRequest.getTurno());
            //Guardar en la base de datos
            horarioBloqueRepository.save(horarioBloque);
            return new ApiResponse("Bloque registrado correctamente",horarioBloque);
        }catch (Exception e){
            return new ApiResponse("Error bloque no registrado",null);
            //Ir a controller para finalizar
        }
    }
        //Listar
        public List<HorarioBloqueDto> listarHorarioBloques() {
            List<HorarioBloqueEntity> bloques = horarioBloqueRepository.findAll();
            return bloques.stream().map(bloque -> {
                HorarioBloqueDto dto = new HorarioBloqueDto();
                dto.setId(bloque.getId());
                dto.setDiaSemana(bloque.getDiaSemana());
                dto.setHoraInicio(bloque.getHoraInicio());
                dto.setHoraFin(bloque.getHoraFin());
                dto.setTurno(bloque.getTurno());
                return dto;
            }).collect(Collectors.toList());
        }
    // Eliminar
    public ApiResponse deleteHorarioBloque(Integer id) {
        try {
            HorarioBloqueEntity bloque = horarioBloqueRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Bloque no encontrado"));

            horarioBloqueRepository.delete(bloque);
            return new ApiResponse("Bloque eliminado correctamente", null);
        } catch (Exception e) {
            return new ApiResponse("Error al eliminar bloque", null);
        }
    }

    // Actualizar
    public ApiResponse updateHorarioBloque(Integer id, HorarioBloqueRequest request) {
        try {
            HorarioBloqueEntity bloque = horarioBloqueRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Bloque no encontrado"));

            bloque.setDiaSemana(request.getDiaSemana());
            bloque.setHoraInicio(request.getHoraInicio());
            bloque.setHoraFin(request.getHoraFin());
            bloque.setTurno(request.getTurno());

            horarioBloqueRepository.save(bloque);
            return new ApiResponse("Bloque actualizado correctamente", bloque);
        } catch (Exception e) {
            return new ApiResponse("Error al actualizar bloque", null);
        }
    }
    // Buscar por ID
    public HorarioBloqueDto findById(Integer id) {
        HorarioBloqueEntity bloque = horarioBloqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bloque no encontrado"));

        HorarioBloqueDto dto = new HorarioBloqueDto();
        dto.setId(bloque.getId());
        dto.setDiaSemana(bloque.getDiaSemana());
        dto.setHoraInicio(bloque.getHoraInicio());
        dto.setHoraFin(bloque.getHoraFin());
        dto.setTurno(bloque.getTurno());
        return dto;
    }

}
