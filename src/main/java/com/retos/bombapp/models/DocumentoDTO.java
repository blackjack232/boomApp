package com.retos.bombapp.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.retos.bombapp.entities.Proyectos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentoDTO implements Serializable {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("nombre")
    private String name;

    @JsonProperty("link")
    private String link;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("proyecto")
    private Proyectos proyectos;

    @JsonProperty("proyectoId")
    private Long projectId;
}
