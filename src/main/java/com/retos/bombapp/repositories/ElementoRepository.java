package com.retos.bombapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retos.bombapp.entities.Asignaciones;
import com.retos.bombapp.entities.Elementos;

/**
 * Repositorio JPA para {@link Elementos}
 */
public interface ElementoRepository extends JpaRepository<Elementos, Long> {
    /**
     * Obtiene todos los elementos por asignaciones de la base de datos
     * 
     * @param asignaciones Asignaciones
     * @param status String
     * @return List<Elementos>
     */
    List<Elementos> findAllByAsignacionesAndStatus(Asignaciones asignaciones, String status);
}
