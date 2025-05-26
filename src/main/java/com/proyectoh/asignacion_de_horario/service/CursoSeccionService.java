package com.proyectoh.asignacion_de_horario.service;

import com.proyectoh.asignacion_de_horario.dto.request.CursoSeccionRequest;
import com.proyectoh.asignacion_de_horario.dto.response.ApiResponse;
import com.proyectoh.asignacion_de_horario.persistence.entity.CursoSeccionEntity;
import com.proyectoh.asignacion_de_horario.persistence.repository.CursoSeccionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CursoSeccionService {
    @Autowired
    private CursoSeccionRepository cursoSeccionRepository;
    //vamos a controller
    

}
