package com.proyectoh.asignacion_de_horario.service;

import com.proyectoh.asignacion_de_horario.dto.DocenteUnidadDto;
import com.proyectoh.asignacion_de_horario.dto.request.DocenteUnidadRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.DocenteEntity;
import com.proyectoh.asignacion_de_horario.persistence.entity.DocenteUnidadEntity;
import com.proyectoh.asignacion_de_horario.persistence.entity.UnidadAcademicaEntity;
import com.proyectoh.asignacion_de_horario.persistence.repository.DocenteRepository;
import com.proyectoh.asignacion_de_horario.persistence.repository.DocenteUnidadRepository;
import com.proyectoh.asignacion_de_horario.persistence.repository.UnidadAcademicaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DocenteUnidadService {
    //Anotacion Autowired principal Docente Unidad
    @Autowired
    private DocenteUnidadRepository docenteUnidadRepository;
    //Buscar Id de Docente
    @Autowired
    private DocenteRepository docenteRepository;
    //Buscar Id de Unidad Academica
    @Autowired
    private UnidadAcademicaRepository unidadAcademicaRepository;
    //Vamos a controller antes de proseguir
    public ApiResponse createDocenteUnidad(DocenteUnidadRequest docenteUnidadRequest){
        try {
            //Buscamos el doncete Id
            DocenteEntity docenteEntity = docenteRepository.findById(docenteUnidadRequest.getDocenteId())
                    .orElseThrow(()->new ClassNotFoundException("Docente Unidad no encontrado"));
            //Buscamos la unidad academica Id
            UnidadAcademicaEntity unidadAcademicaEntity = unidadAcademicaRepository.findById(docenteUnidadRequest.getUnidadAcademicaId())
                    .orElseThrow(()->new ClassNotFoundException("Docente Unidad no encontrado"));
            DocenteUnidadEntity docenteUnidad = new DocenteUnidadEntity();
            docenteUnidad.setDocente(docenteEntity);
            docenteUnidad.setUnidadAcademica(unidadAcademicaEntity);
            //Guardar en la bse de datos
            docenteUnidadRepository.save(docenteUnidad);
            return new ApiResponse("Unidad registrado correctamente",docenteUnidad);

        }catch (Exception e){
            return new ApiResponse("No se registro la Unidad",null);
            //Terminado esto nos vamos al Controller para culminar
        }
    }

    //Metodo Listar
    public List<DocenteUnidadDto> listarDocenteUnidad() {
        List<DocenteUnidadEntity> lista = docenteUnidadRepository.findAll();

        return lista.stream().map(entidad -> {
            DocenteUnidadDto dto = new DocenteUnidadDto();
            dto.setId(entidad.getId());
            dto.setNombreDocente(entidad.getDocente().getNombre() + " " + entidad.getDocente().getApellido());
            dto.setUnidadAcademica(entidad.getUnidadAcademica().getNombre());
            return dto;
        }).collect(Collectors.toList());
    }
    //Eliminar
    public ApiResponse deleteDocenteUnidad(Integer id) {
        try {
            DocenteUnidadEntity entidad = docenteUnidadRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("DocenteUnidad no encontrada"));

            docenteUnidadRepository.delete(entidad);
            return new ApiResponse("DocenteUnidad eliminada correctamente", null);
        } catch (Exception e) {
            return new ApiResponse("Error al eliminar DocenteUnidad", null);
        }
    }

    //Actualizar
    public ApiResponse updateDocenteUnidad(Integer id, DocenteUnidadRequest request) {
        try {
            DocenteUnidadEntity entidad = docenteUnidadRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("DocenteUnidad no encontrada"));

            DocenteEntity docente = docenteRepository.findById(request.getDocenteId())
                    .orElseThrow(() -> new RuntimeException("Docente no encontrado"));

            UnidadAcademicaEntity unidad = unidadAcademicaRepository.findById(request.getUnidadAcademicaId())
                    .orElseThrow(() -> new RuntimeException("Unidad académica no encontrada"));

            entidad.setDocente(docente);
            entidad.setUnidadAcademica(unidad);

            docenteUnidadRepository.save(entidad);

            return new ApiResponse("DocenteUnidad actualizada correctamente", entidad);
        } catch (Exception e) {
            return new ApiResponse("Error al actualizar DocenteUnidad", null);
        }
    }

    //Buscar por Id
    public DocenteUnidadDto findById(Integer id) {
        DocenteUnidadEntity entidad = docenteUnidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DocenteUnidad no encontrada"));

        DocenteUnidadDto dto = new DocenteUnidadDto();
        dto.setId(entidad.getId());
        dto.setNombreDocente(entidad.getDocente().getNombre() + " " + entidad.getDocente().getApellido());
        dto.setUnidadAcademica(entidad.getUnidadAcademica().getNombre());
        return dto;
    }

}
