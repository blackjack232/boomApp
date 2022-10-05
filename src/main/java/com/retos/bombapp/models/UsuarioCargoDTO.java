package com.retos.bombapp.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.retos.bombapp.entities.Usuarios;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioCargoDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("usuarioId")
    private Usuarios usuarios;

    @JsonProperty("cargoId")
    private Long positionId;
}
