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
 * Entidad para la tabla permisos
 */
@Entity
@Table(name = "permisos")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Permisos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Comment("Identificador primario del permiso")
    @Column(name = "id_permiso")
    private Long id;

    @JsonProperty("accion_consultar")
    @Comment("Acción consultar del permiso")
    @Column(name = "accion_consultar", nullable = false, columnDefinition = "boolean default false")
    private boolean consultAction;

    @JsonProperty("accion_crear")
    @Comment("Acción crear del permiso")
    @Column(name = "accion_crear", nullable = false, columnDefinition = "boolean default false")
    private boolean createAction;

    @JsonProperty("accion_elimnar")
    @Comment("Acción eliminar del permiso")
    @Column(name = "accion_eliminar", nullable = false, columnDefinition = "boolean default false")
    private boolean deleteAction;

    @JsonProperty("accion_actualizar")
    @Comment("Acción actualizar del permiso")
    @Column(name = "accion_actualizar", nullable = false, columnDefinition = "boolean default false")
    private boolean updateAction;

    @JsonProperty("rol")
    @Comment("Identificador foráneo del rol del permiso")
    @ManyToOne
    @JoinColumn(name = "id_rol", foreignKey = @ForeignKey(name = "fk_permisos_roles"))
    private Roles roles;

    @JsonProperty("funcionalidad")
    @Comment("Identificador foráneo de la funcionalidad del permiso")
    @ManyToOne
    @JoinColumn(name = "id_funcionalidad", foreignKey = @ForeignKey(name = "fk_permisos_funcionalidades"))
    private Funcionalidades funcionalidades;
}
