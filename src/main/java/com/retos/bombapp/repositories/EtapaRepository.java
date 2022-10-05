package com.retos.bombapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retos.bombapp.entities.Etapas;
import com.retos.bombapp.entities.Proyectos;

/**
 * Repositorio JPA para {@link Etapas}
 */
public interface EtapaRepository extends JpaRepository<Etapas, Long> {
    /**
     * Obtiene todas las etapas por un proyecto
     * 
     * @param proyectos Proyectos
     * @return List<Etapas>
     */
    List<Etapas> findAllByProyectos(Proyectos proyectos);

    /**
     * Elimina todas las etapas de un proyecto
     * 
     * @param proyectos
     */
    void deleteAllByProyectos(Proyectos proyectos);

    /**
     * Valida si existen etapas por proyecto
     * 
     * @param proyectos Proyectos
     * @return boolean
     */
    boolean existsByProyectos(Proyectos proyectos);
}
