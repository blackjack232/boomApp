package com.retos.bombapp.services;

import java.util.List;

import com.retos.bombapp.entities.Proyectos;
import com.retos.bombapp.models.ProyectoDTO;

public interface ProyectoService {
    /**
     * Obtiene todos los proyectos por el rol
     * 
     * @param token String
     * @return List<ProyectoDTO>
     */
    List<ProyectoDTO> getAllProjectsByRol(String token);

    /**
     * Obtiene el proyecto por id
     * 
     * @param id Long
     * @return ProyectoDTO
     */
    ProyectoDTO getById(Long id);

    /**
     * Inserta un proyecto
     * 
     * @param proyectoDTO
     * @return Proyectos
     */
    Proyectos insertProject(ProyectoDTO proyectoDTO);

    /**
     * Actualiza un proyecto
     * 
     * @param id          Long
     * @param proyectoDTO ProyectoDTO
     * @return Proyectos
     */
    Proyectos updateProject(Long id, ProyectoDTO proyectoDTO);

    /**
     * Elimina un proyecto
     * 
     * @param id Long
     */
    void deleteProject(Long id);
}
