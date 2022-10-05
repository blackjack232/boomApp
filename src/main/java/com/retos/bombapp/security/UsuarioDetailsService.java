package com.retos.bombapp.security;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.entities.Usuarios;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Servicio heredado de UserDetailsService
 */
@Service
public class UsuarioDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Método para la consulta del usuario y validar sus credenciales
     *
     * @param email String
     * @return UserDetails
     * @throws UsernameNotFoundException UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuarios usuario = consultUserByEmail(email);

        User.UserBuilder userBuilder = User
                .withUsername(email)
                .password(usuario.getPassword())
                .roles(usuario.getRoles().getName());

        return userBuilder.build();
    }

    /**
     * Consulta el usuario por email en la base de datos
     * @param email String
     * @return Usuarios
     */
    public Usuarios consultUserByEmail(String email) {
        Usuarios usuario = usuarioRepository.findByEmail(email).orElse(null);

        if (Objects.isNull(usuario)) {
            throw new BombappException(CodigoRespuestaApi.CREDENTIALS_INCORRECT);
        }

        if (usuario.getStatus().equals(Constantes.INACTIVE)) {
            throw new BombappException(CodigoRespuestaApi.USER_INACTIVE);
        }

        if (Objects.isNull(usuario.getRoles())) {
            throw new BombappException(CodigoRespuestaApi.NO_ROLES_ASSOCIATE);
        }

        return usuario;
    }
}
