package com.retos.bombapp.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ElementoDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("nombre")
    private String name;

    @JsonProperty("urlEntregable")
    private String deliverableUrl;

    @JsonProperty("descripcion")
    private String description;

    @JsonProperty("elementoPadreId")
    private Long parentElementId;

    @JsonProperty("fechaCarga")
    private String uploadDate;

    @JsonProperty("estadoId")
    private Long statusId;
    
    @JsonProperty("estado")
    private String status;

    @JsonProperty("asignacionId")
    private Long assignmentId;
}
