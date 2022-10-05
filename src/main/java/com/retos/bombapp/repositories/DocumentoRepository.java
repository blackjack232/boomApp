package com.retos.bombapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retos.bombapp.entities.Documentos;
import com.retos.bombapp.entities.Proyectos;

/**
 * Repositorio JPA para {@link Documentos}
 */
public interface DocumentoRepository extends JpaRepository<Documentos, Long> {
    /**
     * Obtiene la lista de documentos asociados a un proyecto de la base de datos
     * @param proyectos Proyectos
     * @return List<Documentos>
     */
    List<Documentos> findAllByProyectos(Proyectos proyectos);
}
