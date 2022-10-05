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
 * Entidad para la tabla comentarios
 */
@Entity
@Table(name = "comentarios")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comentarios implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Comment("Identificador primario del comentario")
    @Column(name = "id_comentario")
    private Long id;

    @JsonProperty("comentarioPadreId")
    @Comment("Identificador primario de sí mismo")
    @Column(name = "id_comentario_padre")
    private Long idCommentParent;

    @JsonProperty("revisado")
    @Comment("Bandera que indica si el comentario se revisó")
    @Column(name = "revisado")
    private boolean reviewed;

    @JsonProperty("texto")
    @Comment("Texto del comentario")
    @Column(name = "texto")
    private String text;

    @JsonProperty("momento")
    @Comment("Momento en que se hace un rayado al video y es 0 si es una imágen del comentario")
    @Column(name = "momento")
    private Long moment;

    @JsonProperty("fechaCreacion")
    @Comment("Momento en que se hace un rayado al video y es 0 si es una imágen del comentario")
    @Column(name = "fecha_creacion")
    private Date creationDate;

    @JsonProperty("dibujo")
    @Lob
    @Comment("Dibujo o imagen del comentario")
    @Column(name = "dibujo", nullable = false, columnDefinition = "Text")
    private String picture;

    @JsonProperty("elemento")
    @Comment("Identificador foráneo del elemento del comentario")
    @ManyToOne
    @JoinColumn(name = "id_elemento", foreignKey = @ForeignKey(name = "fk_comentarios_elementos"))
    private Elementos elementos;

    @JsonProperty("usuario")
    @Comment("Identificador foráneo del elemento del comentario")
    @ManyToOne
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "fk_comentarios_usuarios"))
    private Usuarios usuarios;
}
