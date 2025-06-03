package com.proyectoh.asignacion_de_horario.service;

import com.proyectoh.asignacion_de_horario.dto.HorarioDto;
import com.proyectoh.asignacion_de_horario.dto.request.HorarioRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.*;
import com.proyectoh.asignacion_de_horario.persistence.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HorarioService {
    //Anotacion Autowired Principal
    @Autowired
    private HorarioRepository horarioRepository;
    //Buscar Id de docente
    @Autowired
    private DocenteRepository docenteRepository;
    //Buscar Id seccion
    @Autowired
    private CursoSeccionRepository seccionRepository;
    //Buscar Id aula
    @Autowired
    private AulaRepository aulaRepository;
    //Buscar bloque
    @Autowired
    private HorarioBloqueRepository bloqueRepository;

    //Vamos a controller antes de proseguir
public ApiResponse createHorario(HorarioRequest horarioRequest) {
    try {
        //Buscamos el docente Id
        DocenteEntity docenteEntity = docenteRepository.findById(horarioRequest.getDocenteId())
                .orElseThrow(() -> new ClassNotFoundException("Docente Unidad no encontrado"));
        CursoSeccionEntity cursoSeccion = seccionRepository.findById(horarioRequest.getCursoSeccionId())
                .orElseThrow(() -> new ClassNotFoundException("Curso seccion no encontrado"));
        AulaEntity aula = aulaRepository.findById(horarioRequest.getAulaId())
                .orElseThrow(() -> new ClassNotFoundException("Aula no encontrada"));
        HorarioBloqueEntity horarioBloque = bloqueRepository.findById(horarioRequest.getBloqueHorarioId())
                .orElseThrow(() -> new ClassNotFoundException("Bloque no encontrado"));
        HorarioEntity horarioEntity = new HorarioEntity();
        //Setear
        horarioEntity.setDocente(docenteEntity);
        horarioEntity.setCursoSeccion(cursoSeccion);
        horarioEntity.setAula(aula);
        horarioEntity.setBloqueHorario(horarioBloque);

        //Guardar base de datos
        horarioRepository.save(horarioEntity);
        return new ApiResponse("Horario registrado correctamente", horarioEntity);
    } catch (Exception e) {
        return new ApiResponse("No se registro el horario", null);

    }
}
        //Listar
        public List<HorarioDto> listarHorarios() {
            List<HorarioEntity> lista = horarioRepository.findAll();

            return lista.stream().map(horario -> {
                HorarioDto dto = new HorarioDto();

                // Seteamos el ID del horario
                dto.setId(horario.getId());

                // Nombre del docente (nombre + apellido)
                String nombreCompletoDocente = horario.getDocente().getNombre() + " " + horario.getDocente().getApellido();
                dto.setNombreDocente(nombreCompletoDocente);

                // Nombre del curso-sección (ejemplo: "Matemática - A1")
                String nombreCursoSeccion = horario.getCursoSeccion().getCurso().getNombre()
                        + " - "
                        + horario.getCursoSeccion().getSeccionAcademica().getNombre();
                dto.setNombreCursoSeccion(nombreCursoSeccion);

                // Nombre del aula
                dto.setNombreAula(horario.getAula().getNombre());

                // Nombre del bloque horario (ejemplo: "Lunes 08:00 - 10:00")
                HorarioBloqueEntity bloque = horario.getBloqueHorario();
                String nombreBloque = bloque.getDiaSemana() + " " + bloque.getHoraInicio() + " - " + bloque.getHoraFin();
                dto.setNombreBloqueHorario(nombreBloque);

                return dto;
            }).collect(Collectors.toList());
        }
        //Eliminar
    public ApiResponse deleteHorario(Integer id) {
        Optional<HorarioEntity> horarioOptional = horarioRepository.findById(id);
        if (horarioOptional.isPresent()) {
            horarioRepository.deleteById(id);
            return new ApiResponse("Horario eliminado correctamente", null);
        } else {
            return new ApiResponse("Horario no encontrado", null);
        }
    }
        //Actualizar
        public ApiResponse updateHorario(Integer id, HorarioRequest request) {
            try {
                HorarioEntity horario = horarioRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Horario no encontrado"));

                DocenteEntity docente = docenteRepository.findById(request.getDocenteId())
                        .orElseThrow(() -> new ClassNotFoundException("Docente no encontrado"));
                CursoSeccionEntity cursoSeccion = seccionRepository.findById(request.getCursoSeccionId())
                        .orElseThrow(() -> new ClassNotFoundException("Curso-Sección no encontrado"));
                AulaEntity aula = aulaRepository.findById(request.getAulaId())
                        .orElseThrow(() -> new ClassNotFoundException("Aula no encontrada"));
                HorarioBloqueEntity bloque = bloqueRepository.findById(request.getBloqueHorarioId())
                        .orElseThrow(() -> new ClassNotFoundException("Bloque horario no encontrado"));

                horario.setDocente(docente);
                horario.setCursoSeccion(cursoSeccion);
                horario.setAula(aula);
                horario.setBloqueHorario(bloque);

                horarioRepository.save(horario);
                return new ApiResponse("Horario actualizado correctamente", horario);
            } catch (Exception e) {
                return new ApiResponse("Error al actualizar el horario", null);
            }
        }
        //Buscar por id
        public HorarioDto findById(Integer id) {
            HorarioEntity horario = horarioRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Horario no encontrado"));

            HorarioDto dto = new HorarioDto();
            dto.setId(horario.getId());
            dto.setNombreDocente(horario.getDocente().getNombre() + " " + horario.getDocente().getApellido());

            String nombreCursoSeccion = horario.getCursoSeccion().getCurso().getNombre()
                    + " - " + horario.getCursoSeccion().getSeccionAcademica().getNombre();
            dto.setNombreCursoSeccion(nombreCursoSeccion);

            dto.setNombreAula(horario.getAula().getNombre());

            HorarioBloqueEntity bloque = horario.getBloqueHorario();
            String nombreBloque = bloque.getDiaSemana() + " " + bloque.getHoraInicio() + " - " + bloque.getHoraFin();
            dto.setNombreBloqueHorario(nombreBloque);

            return dto;
        }


}


