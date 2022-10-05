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
 * Entidad para la tabla usuarios_asignaciones
 */
@Entity
@Table(name = "usuarios_asignaciones")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuariosAsignaciones implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Comment("Identificador primario del usuario")
    @Column(name = "id_usuario_asignacion")
    private Long id;

    @JsonProperty("usuario")
    @Comment("Identificador foráneo del usuario del usuario_asignación")
    @ManyToOne
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "fk_usuarios_asignaciones_usuarios"))
    private Usuarios usuarios;

    @JsonProperty("asignacion")
    @Comment("Identificador foráneo de la asignación del usuario_asignación")
    @ManyToOne
    @JoinColumn(name = "id_asignacion", foreignKey = @ForeignKey(name = "fk_usuarios_asignaciones_asignaciones"))
    private Asignaciones asignaciones;
}
