package com.retos.bombapp.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AsignacionDTO implements Serializable {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("fechaAsignacion")
    private String assignmentDate;

    @JsonProperty("fechaEsperadaInicio")
    private String expectedStartDate;

    @JsonProperty("fechaEsperadaFin")
    private String expectedEndDate;

    @JsonProperty("fechaRealInicio")
    private String actualStartDate;

    @JsonProperty("fechaRealFin")
    private String actualEndDate;

    @JsonProperty("duracion")
    private Integer duration;

    @JsonProperty("avance")
    private String advance;

    @JsonProperty("estadoId")
    private Long statusId;

    @JsonProperty("etapaId")
    private Long stageId;

    @JsonProperty("tareaId")
    private Long taskId;

    @JsonProperty("nombre")
    private String name;

    @JsonProperty("descripcion")
    private String description;

    @JsonProperty("proyectoId")
    private Long projectId;

    @JsonProperty("usuarios")
    private List<Long> users;
    
    @JsonProperty("estado")
    private String status;
    
    @JsonProperty("vencido")
    private boolean expired;
}
