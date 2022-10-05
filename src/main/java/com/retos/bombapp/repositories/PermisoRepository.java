package com.retos.bombapp.repositories;

import com.retos.bombapp.entities.Permisos;
import com.retos.bombapp.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermisoRepository extends JpaRepository<Permisos, Long> {
    /**
     * Obtiene todos los permisos por el rol
     * @param roles Roles
     * @return List<Permisos>
     */
    List<Permisos> findByRoles(Roles roles);
}
