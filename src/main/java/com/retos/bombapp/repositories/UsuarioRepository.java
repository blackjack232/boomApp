package com.retos.bombapp.repositories;

import com.retos.bombapp.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio JPA para {@link Usuarios}
 */
public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    /**
     * Busca el usuario por su email
     * @param email String
     * @return Optional<Usuarios>
     */
    Optional<Usuarios> findByEmail(String email);
}
