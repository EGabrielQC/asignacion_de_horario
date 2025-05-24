package com.proyectoh.asignacion_de_horario.persistence.repository;

import com.proyectoh.asignacion_de_horario.persistence.entity.UnidadAcademicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadAcademicaRepository extends JpaRepository<UnidadAcademicaEntity, Integer> {
}
