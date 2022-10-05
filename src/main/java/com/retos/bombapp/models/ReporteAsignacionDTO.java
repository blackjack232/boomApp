package com.retos.bombapp.models;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.retos.bombapp.enums.AsignacionesReporteEnum;
import com.retos.bombapp.enums.OrdenamientoEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReporteAsignacionDTO implements Serializable {
    @JsonProperty("proyecto")
    private Long projectId;

    @JsonProperty("etapa")
    private Long stageId;

    @JsonProperty("campo")
    private AsignacionesReporteEnum field;

    @JsonProperty("orden")
    private OrdenamientoEnum sort;

    @JsonProperty("responsable")
    private String responsible;

    @JsonProperty("tarea")
    private String task;

    @JsonProperty("fechaInicio")
    private String startDate;

    @JsonProperty("fechaFin")
    private String endDate;

    @JsonIgnore
    private Date startDateD;

    @JsonIgnore
    private Date endDateD;

    @JsonProperty("estado")
    private String status;
}
