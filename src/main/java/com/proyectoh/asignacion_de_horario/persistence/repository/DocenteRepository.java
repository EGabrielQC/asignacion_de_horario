package com.proyectoh.asignacion_de_horario.persistence.repository;
import com.proyectoh.asignacion_de_horario.persistence.entity.DocenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocenteRepository extends JpaRepository<DocenteEntity,Integer> {
}
