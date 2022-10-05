package com.retos.bombapp.services;

import java.util.List;

import com.retos.bombapp.entities.Elementos;
import com.retos.bombapp.models.ElementoDTO;

public interface ElementoService {
    /**
     * Obtiene todos los elementos por asignación
     * 
     * @param id Long
     * @return List<Elementos>
     */
    List<ElementoDTO> getAllElementsByAssignment(Long id);

    /**
     * Obtiene el elemento por id
     * 
     * @param id Long
     * @return Elementos
     */
    ElementoDTO getElementById(Long id);

    /**
     * Insertar un elemento
     * 
     * @param elementoDTO ElementoDTO
     * @return Elementos
     */
    Elementos insertElement(ElementoDTO elementoDTO);

    /**
     * Actualizar un elemento
     * 
     * @param id          Long
     * @param elementoDTO ElementoDTO
     * @return Elementos
     */
    Elementos updateElement(Long id, ElementoDTO elementoDTO);

    /**
     * Cambia el estado de un elemento
     * 
     * @param id Long
     * @param status String
     */
    void changeStatusElement(Long id, String status);
}
