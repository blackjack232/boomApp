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
public class EtapaDTO implements Serializable {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("etapaParametroId")
    private Long stageParameterId;

    @JsonProperty("etapaParametroNombre")
    private String stageParameterName;

    @JsonProperty("proyectoId")
    private Long projectId;

    @JsonProperty("parametrosId")
    private List<Long> parametersStageId;
}
