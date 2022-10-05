package com.retos.bombapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.retos.bombapp.entities.Proyectos;

/**
 * Repositorio JPA para {@link Proyectos}
 */
public interface ProyectoRepository extends JpaRepository<Proyectos, Long> {
    /**
     * Obtiene todos los proyectos por estado
     * 
     * @param status String
     * @return List<Proyectos>
     */
    List<Proyectos> findAllByStatus(String status);

    /**
     * Obtiene todos los proyectos por usuario
     * 
     * @param id Long
     * @return List<Proyectos>
     */
    @Query(value = "select distinct  p.* " +
            "from proyectos p " +
            "inner join asignaciones a on (p.id_proyecto = a.id_proyecto) " +
            "inner join usuarios_asignaciones ua on (a.id_asignacion = ua.id_asignacion) " +
            "where ua.id_usuario = ?1 and p.estado = ?2", nativeQuery = true)
    List<Proyectos> getAllByUserAndStatus(Long id, String status);
}
