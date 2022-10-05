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
 * Entidad para la tabla funcionalidades
 */
@Entity
@Table(
        name = "funcionalidades",
        indexes = {@Index(name = "uk_funcionalidades_nombre", columnList = "nombre", unique = true)}
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Funcionalidades implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Comment("Identificado primario de la funcionalidad")
    @Column(name = "id_funcionalidad")
    private Long id;

    @JsonProperty("nombre")
    @Comment("Nombre de la funcionalidad")
    @Column(name = "nombre", nullable = false)
    private String name;

    @JsonProperty("descripcion")
    @Comment("Descripción de la funcionalidad")
    @Column(name = "descripcion")
    private String description;

    @JsonProperty("codigo")
    @Comment("Código de la funcionalidad")
    @Column(name = "codigo", nullable = false)
    private String code;

    @JsonProperty("estado")
    @Comment("Estado de la funcionalidad")
    @Column(name = "estado", nullable = false, columnDefinition = "boolean default true")
    private boolean status;
}
