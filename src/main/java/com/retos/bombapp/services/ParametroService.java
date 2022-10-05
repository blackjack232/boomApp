package com.retos.bombapp.services;

import com.retos.bombapp.entities.Parametros;
import com.retos.bombapp.models.ParametroDTO;

import java.util.List;

public interface ParametroService {
    /**
     * Obtiene todos los tipos de parámetros
     * @return List<ParametroDTO>
     */
    List<ParametroDTO> getAllOnlyTypes();

    /**
     * Obtiene todos los parámetros por tipo
     * @param type String
     * @return List<ParametroDTO>
     */
    List<ParametroDTO> getAllByType(String type);

    /**
     * Inserta un parámetro en la base de datos
     * @param parametroDTO ParametroDTO
     * @return Parametros
     */
    Parametros insertParameter(ParametroDTO parametroDTO);

    /**
     * Actualiza un parámetro en la base de datos por id
     * @param id Long
     * @param parametroDTO ParametroDTO
     * @return Parametros
     */
    Parametros updateParameter(Long id, ParametroDTO parametroDTO);

    /**
     * Cambia el estado del parámetro
     * @param id Long
     * @param status String
     */
    void changeStatus(Long id, String status);

    /**
     * Cambia el orden del parámetro
     * @param id Long
     * @param order boolean
     */
    void changeOrder(Long id, boolean order);
}
