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
 * Entidad para la tabla proyectos
 */
@Entity
@Table(
        name = "proyectos",
        indexes = {@Index(name = "uk_proyectos_nombre", columnList = "nombre", unique = true)}
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Proyectos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Comment("Identificador primario del proyecto")
    @Column(name = "id_proyecto")
    private Long id;

    @JsonProperty("nombre")
    @Comment("Nombre del proyecto")
    @Column(name = "nombre", nullable = false)
    private String name;

    @JsonProperty("tipo")
    @Comment("Tipo del proyecto")
    @Column(name = "tipo", nullable = false)
    private Long type;

    @JsonProperty("fechaInicial")
    @Comment("Fecha de inicio del proyecto")
    @Column(name = "fecha_inicial")
    private Date startDate;

    @JsonProperty("fechaFinal")
    @Comment("Fecha de fin del proyecto")
    @Column(name = "fecha_final")
    private Date endDate;

    @JsonProperty("estado")
    @Comment("Estado del proyecto")
    @Column(name = "estado", nullable = false, columnDefinition = "varchar(1) default 'A'")
    private String status;

    @JsonProperty("tecnica")
    @Comment("Técnica del proyecto")
    @Column(name = "tecnica")
    private Long technique;

    @JsonProperty("temporada")
    @Comment("Temporada del proyecto")
    @Column(name = "temporada")
    private short season;

    @JsonProperty("numeroCapitulos")
    @Comment("Número de capítulo del proyecto")
    @Column(name = "numeroCapitulos")
    private short numberChapter;

    @JsonProperty("capitulo")
    @Comment("Capítulo del proyecto")
    @Column(name = "capitulo")
    private short chapter;

    @JsonProperty("duracion")
    @Comment("Duración del proyecto")
    @Column(name = "duracion")
    private Integer duration;

    @JsonProperty("cuadrosPorSegundo")
    @Comment("Cuadros por segundo del proyecto")
    @Column(name = "cuadrosPorSegundo")
    private short frameRate;

    @JsonProperty("presupuestoTotal")
    @Comment("Presupuesto total del proyecto")
    @Column(name = "presupuestoTotal")
    private Long totalEstimate;

    @JsonProperty("proyectoPadreId")
    @Comment("Identificador foráneo de sí mismo")
    @Column(name = "id_proyecto_padre")
    private Long parentProjectId;

    @JsonProperty("urlImagen")
    @Comment("URL de la imagen del proyecto")
    @Column(name = "url_imagen")
    private String urlImage;

    @JsonProperty("cliente")
    @Comment("Identificador foráneo del cliente del proyecto")
    @ManyToOne
    @JoinColumn(name = "id_cliente", foreignKey = @ForeignKey(name = "fk_proyectos_clientes"))
    private Clientes clientes;
}
