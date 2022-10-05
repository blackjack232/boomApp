package com.retos.bombapp.repositories;

import com.retos.bombapp.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para {@link Roles}
 */
public interface RolRepository extends JpaRepository<Roles, Long> {
    /**
     * Consulta todos los usuarios por su visibilidad
     * @param visibility boolean
     * @return List<Roles>
     */
    List<Roles> findAllByVisibility(boolean visibility);

    /**
     * Consulta el rol por el código
     * @param code String
     * @return Optional<Roles>
     */
    Optional<Roles> findByCode(String code);
}
