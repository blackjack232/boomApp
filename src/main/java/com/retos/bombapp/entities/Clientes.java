package com.retos.bombapp.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entidad para la tabla clientes
 */
@Entity
@Table(
        name = "clientes",
        indexes = {@Index(name = "uk_clientes_nombre", columnList = "nombre", unique = true)}
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Clientes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Comment("Identificador primario del cliente")
    @Column(name = "id_cliente")
    private Long id;

    @JsonProperty("nombre")
    @Comment("Nombre del cliente")
    @Column(name = "nombre", nullable = false)
    private String name;

    @JsonProperty("nit")
    @Comment("NIT del cliente")
    @Column(name = "nit")
    private String nit;

    @JsonProperty("paisOrigen")
    @Comment("País origen del cliente obtenido de la tabla parámetros")
    @Column(name = "id_pais_origen")
    private Long countryOrigin;

    @JsonProperty("estado")
    @Comment("Estado del cliente")
    @Column(name = "estado", nullable = false, columnDefinition = "boolean default true")
    private boolean status;
}
