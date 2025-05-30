package com.proyectoh.asignacion_de_horario.service;


import com.proyectoh.asignacion_de_horario.dto.request.HorarioBloqueRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.HorarioBloqueEntity;
import com.proyectoh.asignacion_de_horario.persistence.repository.HorarioBloqueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
