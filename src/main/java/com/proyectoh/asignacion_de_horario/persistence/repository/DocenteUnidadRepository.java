package com.proyectoh.asignacion_de_horario.persistence.repository;

import com.proyectoh.asignacion_de_horario.persistence.entity.DocenteUnidadEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocenteUnidadRepository extends JpaRepository<DocenteUnidadEntity, Integer> {
}
