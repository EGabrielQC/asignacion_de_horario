package com.proyectoh.asignacion_de_horario.persistence.repository;

import com.proyectoh.asignacion_de_horario.persistence.entity.PeriodoAcademicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodoAcademicoRepository extends JpaRepository<PeriodoAcademicoEntity, Integer> {
}
