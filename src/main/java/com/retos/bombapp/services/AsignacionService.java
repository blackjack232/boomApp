package com.retos.bombapp.services;

import java.util.List;

import com.retos.bombapp.entities.Asignaciones;
import com.retos.bombapp.models.AsignacionDTO;

public interface AsignacionService {
    /**
     * Obtiene todas las asignaciones por proyecto y usuario
     * @param id Long
     * @param token String
     * @return List<AsignacionDTO>
     */
    List<AsignacionDTO> getAllByProject(Long id, String token);

    /**
     * Obtiene una asignación por id
     * @param id Long
     * @return AsignacionDTO
     */
    AsignacionDTO getById(Long id);

    /**
     * Inserta una asignación
     * @param asignacionDTO AsignacionDTO
     * @return Asignaciones
     */
    Asignaciones insertAssignment(AsignacionDTO asignacionDTO);

    /**
     * Actualiza una asignación
     * @param id Long
     * @param asignacionDTO AsignacionDTO
     * @return Asignaciones
     */
    Asignaciones updateAssignment(Long id, AsignacionDTO asignacionDTO);

    /**
     * Eliminar una asignación
     * @param id Long
     */
    void deleteAssignment(Long id);
}
