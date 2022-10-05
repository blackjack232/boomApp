package com.retos.bombapp.services;

import java.util.List;

public interface UsuarioAsignacionService {
    /**
     * Inserta una lista de usuario asignación
     * @param asignacion Long
     * @param listUsuarioAsignacion List<Long>
     */
    void insertAll(Long asignacion, List<Long> listUsuarioAsignacion);

    /**
     * Elimina todas los usuarios asignaciones por id de asignación
     * @param id
     */
    void deleteAllByAsignacion(Long id);

    /**
     * Elimina e inserta una lista de usuarios asignaciones por asignación
     * @param asignacion Asignacion
     * @param listUsuarioAsignacion List<Long>
     */
    void deleteAndInsertAllByAsignacion(Long asignacion, List<Long> listUsuarioAsignacion);
}
