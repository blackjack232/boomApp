package com.retos.bombapp.exceptions;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

/**
 * Excepción básica
 */
@Getter
@AllArgsConstructor
public class BombappException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String code;
    private final String message;
    private final HttpStatus status;

    /**
     * Constructor con {@link CodigoRespuestaApi}
     * @param codigoRespuesta CodigoRespuestaApi
     */
    public BombappException(CodigoRespuestaApi codigoRespuesta) {
        super();
        this.code = codigoRespuesta.getCode();
        this.message = codigoRespuesta.getDescription();
        this.status = codigoRespuesta.getHttpStatus();
    }
}
