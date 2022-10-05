package com.retos.bombapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.retos.bombapp.entities.Asignaciones;
import com.retos.bombapp.entities.Usuarios;
import com.retos.bombapp.entities.UsuariosAsignaciones;

/**
 * Repositorio JPA para {@link UsuariosAsignaciones}
 */
public interface UsuarioAsignacionRepository extends JpaRepository<UsuariosAsignaciones, Long> {
    /**
     * Obtiene todos los usuarios_asignaciones por asignación
     * 
     * @param asignaciones Asignaciones
     * @return List<UsuariosAsignaciones>
     */
    List<UsuariosAsignaciones> findAllByAsignaciones(Asignaciones asignaciones);

    /**
     * Obtiene todos los usuarios_asignaciones por usuario
     * 
     * @param usuarios Usuarios
     * @return List<UsuariosAsignaciones>
     */
    List<UsuariosAsignaciones> findAllByUsuarios(Usuarios usuarios);

    /**
     * Eliminar una lista de usuarios_asignaciones por asignación
     * 
     * @param asignaciones Asignaciones
     */
    void deleteAllByAsignaciones(Asignaciones asignaciones);

    /**
     * Obtiene los usuarios y asignaciones por proyecto y etapa
     * 
     * @param id Long
     * @param idStage Long
     * @return List<UsuariosAsignaciones>
     */
    @Query(value = "select ua.* " +
            "from usuarios_asignaciones ua " +
            "inner join asignaciones a on (ua.id_asignacion = a.id_asignacion) " +
            "where a.id_proyecto = ?1 and a.id_etapa = ?2", nativeQuery = true)
    List<UsuariosAsignaciones> getAllByProjectAndStage(Long id, Long idStage);
}
