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
public class ClienteDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("nombre")
    private String name;

    @JsonProperty("nit")
    private String nit;

    @JsonProperty("paisOrigen")
    private Long countryOrigin;

    @JsonProperty("estado")
    private boolean status;
}
