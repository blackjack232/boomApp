package com.retos.bombapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.retos.bombapp.entities.Asignaciones;
import com.retos.bombapp.entities.Proyectos;

/**
 * Repositorio JPA para {@link Asignaciones}
 */
public interface AsignacionRepository extends JpaRepository<Asignaciones, Long> {
    /**
     * Obtiene todas las asignaciones por proyecto y estado, en orden descendente
     * 
     * @param proyectos Proyectos
     * @param status    String
     * @return List<Asignaciones>
     */
    List<Asignaciones> findAllByProyectosAndStatusOrderByIdDesc(Proyectos proyectos, String status);

    /**
     * Obtiene todas las asignaciones por proyecto y usuario
     * 
     * @param idProject Long
     * @param idUser    Long
     * @return List<Asignaciones>
     */
    @Query(value = "select distinct  a.* " +
            "from asignaciones a " +
            "inner join usuarios_asignaciones ua on (a.id_asignacion = ua.id_asignacion) " +
            "where a.id_proyecto = ?1 and ua.id_usuario = ?2 " +
            "order by a.id_asignacion desc", nativeQuery = true)
    List<Asignaciones> getAllByProjectAndUser(Long idProject, Long idUser);

    /**
     * Valida si existen asignaciones por proyectos
     * 
     * @param proyectos Proyectos
     * @return boolean
     */
    boolean existsByProyectos(Proyectos proyectos);
}
