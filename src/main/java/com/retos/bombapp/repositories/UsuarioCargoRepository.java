package com.retos.bombapp.repositories;

import com.retos.bombapp.entities.Usuarios;
import com.retos.bombapp.entities.UsuariosCargos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio JPA para {@link UsuariosCargos}
 */
public interface UsuarioCargoRepository extends JpaRepository<UsuariosCargos, Long> {
    /**
     * Elimina el usuarioCargo por el usuario
     * @param usuarios Usuarios
     */
    void deleteByUsuarios(Usuarios usuarios);

    /**
     * Consulta todos los usuarios_cargos por usuario
     * @param usuarios Usuarios
     * @return List<UsuariosCargos>
     */
    List<UsuariosCargos> findByUsuarios(Usuarios usuarios);
}
