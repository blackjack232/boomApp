package com.retos.bombapp.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.retos.bombapp.entities.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * DTO para Usuarios
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO implements Serializable {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("nombres")
    private String names;

    @JsonProperty("apellidos")
    private String lastnames;

    @JsonProperty("correo")
    private String email;

    @JsonProperty("celular")
    private String cellphone;

    @JsonProperty("estado")
    private String status;

    @JsonProperty("token")
    private String token;

    @JsonProperty("tipo")
    private Long type;

    @JsonProperty("contrasenaActualizada")
    private boolean updatePassword;

    @JsonProperty("rol")
    private Roles roles;

    @JsonProperty("cargos")
    private List<Long> cargos;

    @JsonProperty("permisos")
    private List<PermisoDTO> listPermissions;

    @JsonProperty("listaCargos")
    private List<CargoDTO> listPositions;
}
