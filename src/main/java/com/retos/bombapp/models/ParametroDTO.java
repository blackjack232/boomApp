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
public class ParametroDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("tipo")
    private String type;

    @JsonProperty("codigo")
    private String code;

    @JsonProperty("nombre")
    private String name;

    @JsonProperty("estado")
    private String status;

    @JsonProperty("orden")
    private short order;

    @JsonProperty("accion")
    private boolean action;
}
