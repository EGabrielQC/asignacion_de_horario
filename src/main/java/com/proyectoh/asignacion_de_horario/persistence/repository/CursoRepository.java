package com.proyectoh.asignacion_de_horario.persistence.repository;

import com.proyectoh.asignacion_de_horario.persistence.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                          //Extención de un repositorio, Id "Integer"
public interface CursoRepository extends JpaRepository<CursoEntity, Integer> {
}
