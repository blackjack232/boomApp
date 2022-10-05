package com.retos.bombapp.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Comment;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "documentos")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Documentos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Comment("Identificador primario del documento")
    @Column(name = "id_documento")
    private Long id;

    @JsonProperty("nombre")
    @Comment("Nombre del documento")
    @Column(name = "nombre")
    private String name;

    @JsonProperty("link")
    @Comment("Enlace del documento")
    @Column(name = "link")
    private String link;

    @JsonProperty("descripcion")
    @Comment("Descripción del documento")
    @Column(name = "descripcion")
    private String descripcion;

    @JsonProperty("proyecto")
    @Comment("Identificador foráneo del proyecto del documento")
    @ManyToOne
    @JoinColumn(name = "id_proyecto", foreignKey = @ForeignKey(name = "fk_documentos_proyectos"))
    private Proyectos proyectos;
}
