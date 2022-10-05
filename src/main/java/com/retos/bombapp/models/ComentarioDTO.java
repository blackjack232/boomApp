package com.retos.bombapp.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.retos.bombapp.entities.Usuarios;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComentarioDTO {
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("elementoId")
    private Long elementId;

    @JsonProperty("usuarioId")
    private Long userId;

    @JsonProperty("comentarioPadreId")
    private Long idCommentParent;
    
    @JsonProperty("revisado")
    private boolean reviewed;

    @JsonProperty("texto")
    private String text;

    @JsonProperty("momento")
    private Long moment;

    @JsonProperty("fechaCreacion")
    private String creationDate;

    @JsonProperty("dibujo")
    private String picture;

    @JsonProperty("usuario")
    private Usuarios usuarios;
}
