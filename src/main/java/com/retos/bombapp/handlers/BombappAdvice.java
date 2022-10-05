package com.retos.bombapp.handlers;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.models.RespuestaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * BombappAdvice para la aplicación
 */
@Slf4j
@RestControllerAdvice
public class BombappAdvice {
    /**
     * Manejo de {@link BombappException}
     *
     * @param runtime BombappException
     * @return ResponseEntity<RespuestaDTO>
     */
    @ExceptionHandler(BombappException.class)
    public ResponseEntity<RespuestaDTO> handle(BombappException runtime) {
        log.info("Error encontrado en bombapp: {}", runtime.getMessage());
        runtime.printStackTrace();
        return ResponseEntity.status(runtime.getStatus())
                .body(RespuestaDTO.builder()
                        .code(runtime.getCode())
                        .message(runtime.getMessage())
                        .status(runtime.getStatus().value())
                        .build());
    }

    /**
     * Manejo de {@link Exception}
     *
     * @param runtime Exception
     * @return ResponseEntity<RespuestaDTO>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaDTO> handle(Exception runtime) {
        log.info("Error encontrado en bombapp: {}", runtime.getMessage());
        runtime.printStackTrace();
        return ResponseEntity.status(CodigoRespuestaApi.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(RespuestaDTO.builder()
                        .code(CodigoRespuestaApi.INTERNAL_SERVER_ERROR.getCode())
                        .message(CodigoRespuestaApi.INTERNAL_SERVER_ERROR.getDescription())
                        .status(CodigoRespuestaApi.INTERNAL_SERVER_ERROR.getHttpStatus().value())
                        .build());
    }
}
