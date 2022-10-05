package com.retos.bombapp.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermisoDTO implements Serializable {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("funcionalidadId")
    private Long functionalityId;

    @JsonProperty("funcionalidadNombre")
    private String functionalityName;

    @JsonProperty("funcionalidadCodigo")
    private String functionalityCode;

    @JsonProperty("accionConsultar")
    private boolean consultAction;

    @JsonProperty("accionCrear")
    private boolean createAction;

    @JsonProperty("accionEliminar")
    private boolean deleteAction;

    @JsonProperty("accionModificar")
    private boolean updateAction;

    @JsonProperty("rolId")
    private Long rolId;
}
