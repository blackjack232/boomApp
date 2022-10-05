package com.retos.bombapp.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entidad para la tabla roles
 */
@Entity
@Table(
        name = "roles",
        indexes = {
                @Index(name = "uk_roles_nombre", columnList = "nombre", unique = true),
                @Index(name = "uk_roles_codigo", columnList = "codigo", unique = true)
        }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Roles implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Comment("Identificador primario del rol")
    @Column(name = "id_rol")
    private Long id;

    @JsonProperty("nombre")
    @Comment("Nombre del rol")
    @Column(name = "nombre", nullable = false)
    private String name;

    @JsonProperty("descripcion")
    @Comment("Descripción del rol")
    @Column(name = "descripcion")
    private String description;

    @JsonProperty("codigo")
    @Comment("Código del rol")
    @Column(name = "codigo", nullable = false)
    private String code;

    @JsonProperty("visibilidad")
    @Comment("Visibilidad del rol")
    @Column(name = "visibilidad", nullable = false, columnDefinition = "boolean default false")
    private boolean visibility;
}
