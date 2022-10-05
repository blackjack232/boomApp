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
 * Entidad para la tabla usuarios_cargos
 */
@Entity
@Table(name = "usuarios_cargos")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosCargos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Comment("Identificador primario del usuario_cargo")
    @Column(name = "id_usuario_cargo")
    private Long id;

    @JsonProperty("usuario")
    @Comment("Identificador foráneo del usuario del usuario_cargo")
    @ManyToOne
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "fk_usuarios_cargos_usuarios"))
    private Usuarios usuarios;

    @JsonProperty("cargoId")
    @Comment("Identificador de cargo obtenido de la tabla parámetros")
    @Column(name = "id_cargo")
    private Long positionId;
}
