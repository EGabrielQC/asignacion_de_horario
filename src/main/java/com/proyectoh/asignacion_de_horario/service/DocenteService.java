package com.proyectoh.asignacion_de_horario.service;

import com.proyectoh.asignacion_de_horario.dto.DocenteDto;
import com.proyectoh.asignacion_de_horario.dto.request.DocenteRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.DocenteEntity;
import com.proyectoh.asignacion_de_horario.persistence.repository.DocenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
    //Listar
    public List<DocenteDto> listarDocentes() {
        return docenteRepository.findAll()
                .stream()
                .map(docente -> new DocenteDto(
                        docente.getId(),
                        docente.getNombre(),
                        docente.getApellido(),
                        docente.getCorreo(),
                        docente.getHoraContratadas(),
                        docente.getMaxHorasPorDia()
                ))
                .collect(Collectors.toList());
    }
    //Eliminar
    public ApiResponse deleteDocente(Integer id) {
        try {
            DocenteEntity docente = docenteRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Docente no encontrado con ID: " + id));

            docenteRepository.delete(docente);
            return new ApiResponse("Docente eliminado correctamente", null);
        } catch (Exception e) {
            log.error("Error al eliminar docente", e);
            return new ApiResponse("Error al eliminar docente", null);
        }
    }

    //Actualizar
    public ApiResponse updateDocente(Integer id, DocenteRequest docenteRequest) {
        try {
            DocenteEntity docente = docenteRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Docente no encontrado con ID: " + id));

            docente.setNombre(docenteRequest.getNombre());
            docente.setApellido(docenteRequest.getApellido());
            docente.setCorreo(docenteRequest.getCorreo());
            docente.setHoraContratadas(docenteRequest.getHoraContratadas());
            docente.setMaxHorasPorDia(docenteRequest.getMaxHorasPorDia());

            docenteRepository.save(docente);
            return new ApiResponse("Docente actualizado correctamente", docente);
        } catch (Exception e) {
            log.error("Error al actualizar docente", e);
            return new ApiResponse("Error al actualizar docente", null);
        }
    }

    //bUSCAR POR iD
    public DocenteDto findById(Integer id) {
        DocenteEntity docente = docenteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Docente no encontrado con ID: " + id));

        return new DocenteDto(
                docente.getId(),
                docente.getNombre(),
                docente.getApellido(),
                docente.getCorreo(),
                docente.getHoraContratadas(),
                docente.getMaxHorasPorDia()
        );
    }


}
