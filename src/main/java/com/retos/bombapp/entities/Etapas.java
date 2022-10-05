package com.retos.bombapp.entities;

import javax.persistence.*;

import org.hibernate.annotations.Comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad para la tabla etapas
 */
@Entity
@Table(name = "etapas")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Etapas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Comment("Identificador primario de la etapa")
    @Column(name = "id_etapa")
    private Long id;

    @JsonProperty("id_etapa_parametro")
    @Comment("Identificador de la etapa obtenido de la tabla parámetros")
    @Column(name = "id_etapa_parametro")
    private Long stageParameterId;

    @JsonProperty("proyecto")
    @Comment("Identificador foráneo del proyecto de la etapa")
    @ManyToOne
    @JoinColumn(name = "id_proyecto", foreignKey = @ForeignKey(name = "fk_etapas_proyectos"))
    private Proyectos proyectos;
}
