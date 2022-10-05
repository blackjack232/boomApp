package com.retos.bombapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retos.bombapp.entities.Comentarios;
import com.retos.bombapp.entities.Elementos;

/**
 * Repositorio JPA para {@link Comentarios}
 */
public interface ComentarioRepository extends JpaRepository<Comentarios, Long> {
    /**
     * Consulta los comentarios por elemento
     * 
     * @param elementos Elementos
     * @return List<Comentarios>
     */
    List<Comentarios> findAllByElementos(Elementos elementos);
}
