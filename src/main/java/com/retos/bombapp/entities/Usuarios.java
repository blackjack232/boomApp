package com.retos.bombapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entidad para la tabla usuarios
 */
@Entity
@Table(
        name = "usuarios",
        indexes = {@Index(name = "uk_usuarios_email", columnList = "email", unique = true)}
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuarios implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Comment("Identificador primario del usuario")
    @Column(name = "id_usuario")
    private Long id;

    @JsonProperty("nombres")
    @Comment("Nombres del usuario")
    @Column(name = "nombres", nullable = false)
    private String names;

    @JsonProperty("apellidos")
    @Comment("Apellidos del usuario")
    @Column(name = "apellidos")
    private String lastnames;

    @JsonProperty("correo")
    @Comment("Email del usuario")
    @Column(name = "email", length = 60, nullable = false)
    private String email;

    @Comment("Contraseña del usuario")
    @JsonIgnore()
    @Column(name = "contrasena", nullable = false)
    private String password;

    @JsonProperty("celular")
    @Comment("Celular del usuario")
    @Column(name = "celular", length = 15)
    private String cellphone;

    @JsonProperty("estado")
    @Comment("Estado del usuario")
    @Column(name = "estado", nullable = false, columnDefinition = "varchar(1) default 'A'")
    private String status;

    @JsonProperty("tipo")
    @Comment("Tipo del usuario obtenido de la tabla parámetros")
    @Column(name = "id_tipo", nullable = false)
    private Long type;

    @JsonProperty("contrasenaActualizada")
    @Comment("Bandera si se actualiza o no la contraseña del usuario")
    @Column(name = "contrasenaActualizada", columnDefinition = "boolean default false")
    private boolean updatePassword;

    @JsonProperty("rol")
    @Comment("Identificador foráneo del rol del usuario")
    @ManyToOne
    @JoinColumn(name = "id_rol", foreignKey = @ForeignKey(name = "fk_usuarios_roles"))
    private Roles roles;
}
