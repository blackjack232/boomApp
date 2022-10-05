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
import java.util.Date;

/**
 * Entidad para la tabla elementos
 */
@Entity
@Table(name = "elementos")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Elementos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Comment("Identificador primario del elemento")
    @Column(name = "id_elemento")
    private Long id;

    @JsonProperty("nombre")
    @Comment("Nombre del elemento")
    @Column(name = "nombre", nullable = false)
    private String name;

    @JsonProperty("urlEntregable")
    @Comment("URL del entregable del elemento")
    @Column(name = "url_entregable")
    private String deliverableUrl;

    @JsonProperty("descripcion")
    @Comment("Descripción del elemento")
    @Column(name = "descripcion")
    private String description;

    @JsonProperty("elementoPadreId")
    @Comment("Identificador foráneo padre de sí mismo")
    @Column(name = "id_elemento_padre")
    private Long parentElementId;

    @JsonProperty("fechaCarga")
    @Comment("Fecha de carga del elemento")
    @Column(name = "fecha_carga")
    private Date uploadDate;

    @JsonProperty("estadoId")
    @Comment("Estado del elemento obtenido de la tabla parámetros")
    @Column(name = "id_estado")
    private Long statusId;
    
    @JsonProperty("estado")
    @Comment("Estado del elemento")
    @Column(name = "estado")
    private String status;

    @JsonProperty("asignacion")
    @Comment("Identificador foráneo de la asignación del elemento")
    @ManyToOne
    @JoinColumn(name = "id_asignacion", foreignKey = @ForeignKey(name = "fk_elementos_asignaciones"))
    private Asignaciones asignaciones;
}
