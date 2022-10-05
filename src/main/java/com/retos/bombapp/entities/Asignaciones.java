package com.retos.bombapp.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Entidad para la tabla asignaciones
 */
@Entity
@Table(name = "asignaciones")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Asignaciones implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Comment("Identificador primario de la asignación")
    @Column(name = "id_asignacion")
    private Long id;

    @JsonProperty("fechaAsignacion")
    @Comment("Fecha de asignación")
    @Column(name = "fecha_asignacion")
    private Date assignmentDate;

    @JsonProperty("fechaEsperadaInicio")
    @Comment("Fecha esperada de inicio de la asignación")
    @Column(name = "fecha_esperada_inicio")
    private Date expectedStartDate;

    @JsonProperty("fechaEsperadaFin")
    @Comment("Fecha esperada final de la asignación")
    @Column(name = "fecha_esperada_fin")
    private Date expectedEndDate;

    @JsonProperty("fechaRealInicio")
    @Comment("Fecha real de inicio de la asignación")
    @Column(name = "fecha_real_inicio")
    private Date actualStartDate;

    @JsonProperty("fechaRealFin")
    @Comment("Fecha real final de la asignación")
    @Column(name = "fecha_real_fin")
    private Date actualEndDate;

    @JsonProperty("duracion")
    @Comment("Duración de la asignación")
    @Column(name = "duracion")
    private Integer duration;

    @JsonProperty("avance")
    @Comment("Avance de la asignación")
    @Column(name = "avance")
    private String advance;

    @JsonProperty("estadoId")
    @Comment("Identificador de estado de la asignación obtenido de la tabla parámetros")
    @Column(name = "id_estado")
    private Long statusId;

    @JsonProperty("etapaId")
    @Comment("Identificador de etapa de la asignación obtenido de la tabla parámetros")
    @Column(name = "id_etapa")
    private Long stageId;

    @JsonProperty("taskId")
    @Comment("Identificador de tarea de la asignación obtenido de la tabla parámetros")
    @Column(name = "id_tarea")
    private Long taskId;

    @JsonProperty("nombre")
    @Comment("Nombre de la asignación")
    @Column(name = "nombre")
    private String name;

    @JsonProperty("descripcion")
    @Comment("Descripción de la asignación")
    @Column(name = "descripcion")
    private String description;

    @JsonProperty("estado")
    @Comment("Estado de la asignación")
    @Column(name = "estado")
    private String status;

    @JsonProperty("proyectos")
    @Comment("Identificador foráneo del proyecto de la asignación")
    @ManyToOne
    @JoinColumn(name = "id_proyecto", foreignKey = @ForeignKey(name = "fk_asignaciones_proyectos"))
    private Proyectos proyectos;
}
