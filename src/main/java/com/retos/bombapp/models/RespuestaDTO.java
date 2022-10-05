package com.retos.bombapp.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para respuesta de los servicios
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaDTO {
    private String code;
    private String message;
    private Integer status;
    private Object data;

    public RespuestaDTO(String code, String message, Integer status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
