package com.proyectoh.asignacion_de_horario.service;

import com.proyectoh.asignacion_de_horario.dto.UnidadAcademicaDto;
import com.proyectoh.asignacion_de_horario.dto.request.UnidadAcademicaRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.UnidadAcademicaEntity;
import com.proyectoh.asignacion_de_horario.persistence.repository.UnidadAcademicaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<UnidadAcademicaDto> listarUnidadesAcademicas() {
        List<UnidadAcademicaEntity> lista = unidadAcademicaRepository.findAll();

        return lista.stream().map(unidad -> {
            UnidadAcademicaDto dto = new UnidadAcademicaDto();
            dto.setId(unidad.getId());
            dto.setNombre(unidad.getNombre());
            return dto;
        }).collect(Collectors.toList());
    }
    // Eliminar unidad académica
    public ApiResponse deleteUnidadAcademica(Integer id) {
        try {
            UnidadAcademicaEntity unidad = unidadAcademicaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Unidad académica no encontrada"));

            unidadAcademicaRepository.delete(unidad);
            return new ApiResponse("Unidad eliminada correctamente", null);
        } catch (Exception e) {
            return new ApiResponse("Error al eliminar la unidad académica", null);
        }
    }

    // Actualizar unidad académica
    public ApiResponse updateUnidadAcademica(Integer id, UnidadAcademicaRequest request) {
        try {
            UnidadAcademicaEntity unidad = unidadAcademicaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Unidad académica no encontrada"));

            unidad.setNombre(request.getNombre());
            unidadAcademicaRepository.save(unidad);

            return new ApiResponse("Unidad actualizada correctamente", unidad);
        } catch (Exception e) {
            return new ApiResponse("Error al actualizar la unidad académica", null);
        }
    }
    // Buscar unidad por ID
    public UnidadAcademicaDto findById(Integer id) {
        UnidadAcademicaEntity unidad = unidadAcademicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidad académica no encontrada"));

        return new UnidadAcademicaDto(unidad.getId(), unidad.getNombre());
    }
}
