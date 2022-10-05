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
 * Entidad para la tabla cargos
 */
@Entity
@Table(
        name = "parametros",
        indexes = {
                @Index(name = "uk_parametros_nombre", columnList = "nombre", unique = true),
                @Index(name = "uk_parametros_codigo", columnList = "codigo", unique = true)
        }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Parametros implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Comment("Identificado primario del parámetro")
    @Column(name = "id_parametro")
    private Long id;

    @JsonProperty("tipo")
    @Comment("Tipo del parámetro")
    @Column(name = "tipo", nullable = false)
    private String type;

    @JsonProperty("codigo")
    @Comment("Código del parámetro")
    @Column(name = "codigo", nullable = false)
    private String code;

    @JsonProperty("nombre")
    @Comment("Nombre del parámetro")
    @Column(name = "nombre", nullable = false)
    private String name;

    @JsonProperty("estado")
    @Comment("Estado del parámetro")
    @Column(name = "estado", nullable = false, columnDefinition = "varchar(1) default 'A'")
    private String status;

    @JsonProperty("orden")
    @Comment("Orden del parámetro")
    @Column(name = "orden", nullable = false)
    private short order;
}
