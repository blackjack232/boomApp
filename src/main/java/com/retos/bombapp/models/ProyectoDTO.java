package com.retos.bombapp.models;

import java.io.Serializable;

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
public class ProyectoDTO implements Serializable {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("nombre")
    private String name;

    @JsonProperty("tipo")
    private Long type;

    @JsonProperty("fechaInicial")
    private String startDate;

    @JsonProperty("fechaFinal")
    private String endDate;

    @JsonProperty("estado")
    private String status;

    @JsonProperty("tecnica")
    private Long technique;

    @JsonProperty("temporada")
    private short season;

    @JsonProperty("numeroCapitulos")
    private short numberChapter;

    @JsonProperty("capitulo")
    private short chapter;

    @JsonProperty("duracion")
    private Integer duration;

    @JsonProperty("cuadrosPorSegundo")
    private short frameRate;

    @JsonProperty("presupuestoTotal")
    private Long totalEstimate;

    @JsonProperty("proyectoPadreId")
    private Long parentProjectId;

    @JsonProperty("urlImagen")
    private String urlImage;

    @JsonProperty("idCliente")
    private Long clientId;

    @JsonProperty("tieneAsignaciones")
    private boolean hasAssignments;

    @JsonProperty("tieneEtapas")
    private boolean hasStages;
}
