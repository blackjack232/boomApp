package com.retos.bombapp.services;

import com.retos.bombapp.models.TokenDTO;

public interface UtilService {
    /**
     * Convierte de un JSON a un Objeto DTO
     * @param json String
     * @param object Object
     * @return Object
     */
    Object fromJsonToDTO(String json, Object object);

    /**
     * Mapea un objeto DTO a otro DTO
     * @param obj Object
     * @param objMap Object
     * @return Object
     */
    Object mapDTO(Object obj, Object objMap);

    /**
     * Escribe un objeto DTO a String
     * @param obj Object
     * @return String
     */
    String writeToString(Object obj);

    /**
     * Deocdifica los datos del payload del token y los retorna en un DTO
     * @param token
     * @return TokenDTO 
     */
    TokenDTO decodePayloadToMapDTO(String token);
}
