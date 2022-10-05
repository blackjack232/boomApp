package com.retos.bombapp.handlers;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.models.RespuestaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Manejo de respuestas de los servicios
 *
 * @author marangel
 */
public class ResponseHandler {

    private ResponseHandler() throws BombappException {
        throw new BombappException(CodigoRespuestaApi.INTERNAL_SERVER_ERROR);
    }

    /**
     * Generación de respuesta usando {@link RespuestaDTO}
     *
     * @param code String
     * @param message String
     * @param status HttpStatus
     * @param responseObj Object
     * @return ResponseEntity<Object>
     */
    public static ResponseEntity<Object> generarResponse(String code, String message, HttpStatus status, Object responseObj) {
        return new ResponseEntity<>(new RespuestaDTO(code, message, status.value(), responseObj), status);
    }

    /**
     * Generación de respuesta usando {@link RespuestaDTO}
     *
     * @param code String
     * @param message String
     * @param status HttpStatus
     * @return ResponseEntity<Object>
     */
    public static ResponseEntity<Object> generarResponse(String code, String message, HttpStatus status) {
        return new ResponseEntity<>(new RespuestaDTO(code, message, status.value()), status);
    }
}
