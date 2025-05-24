package com.proyectoh.asignacion_de_horario.persistence.repository;

import com.proyectoh.asignacion_de_horario.persistence.entity.AulaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 //Acceso a la base de datos // es opcional ponerlo
public interface AulaRepository extends JpaRepository<AulaEntity,Integer> {
}
