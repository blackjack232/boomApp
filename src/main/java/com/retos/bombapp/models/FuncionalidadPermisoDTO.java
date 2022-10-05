package com.retos.bombapp.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FuncionalidadPermisoDTO implements Serializable {
    @JsonProperty("rolId")
    private Long rolId;

    @JsonProperty("funcionalidades")
    private List<PermisoDTO> list;

    @JsonProperty("listaFuncionalidades")
    private List<Long> listFunctionalities;
}
