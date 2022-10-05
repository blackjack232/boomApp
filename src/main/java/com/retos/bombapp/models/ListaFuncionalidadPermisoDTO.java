package com.retos.bombapp.models;

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
public class ListaFuncionalidadPermisoDTO implements Serializable {
    @JsonProperty("lista")
    private List<FuncionalidadPermisoDTO> list;
}
