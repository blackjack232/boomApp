package com.retos.bombapp.repositories;

import com.retos.bombapp.entities.Parametros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para {@link Parametros}
 */
public interface ParametroRepository extends JpaRepository<Parametros, Long> {

    /**
     * Consulta todos los parámetros por el tipo
     *
     * @param type String
     * @return List<Parametros>
     */
    List<Parametros> findByType(String type);

    /**
     * Consulta todos los parámetros activos
     *
     * @param status String
     * @return List<Parametros>
     */
    List<Parametros> findAllByStatus(String status);

    /**
     * Obtiene el parámetro por tipo y nombre
     *
     * @param type String
     * @param name String
     * @return Parametros
     */
    Optional<Parametros> findByTypeAndNameIgnoreCase(String type, String name);
}
