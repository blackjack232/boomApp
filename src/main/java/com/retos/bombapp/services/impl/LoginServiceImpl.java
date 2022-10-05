package com.retos.bombapp.services.impl;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.entities.Usuarios;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.models.LoginDTO;
import com.retos.bombapp.models.UsuarioDTO;
import com.retos.bombapp.security.JwtTokenUtil;
import com.retos.bombapp.security.UsuarioDetailsService;
import com.retos.bombapp.services.LoginService;
import com.retos.bombapp.services.PermisoService;
import com.retos.bombapp.services.UtilService;
import com.retos.bombapp.utils.UtilGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio que autentica el usuario en la aplicación
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UsuarioDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UtilService utilService;

    @Autowired
    private PermisoService permisoService;

    /**
     * {@inheritDoc}
     */
    @Override
    public UsuarioDTO authenticate(LoginDTO loginDTO) {
        Usuarios usuario = userDetailsService.consultUserByEmail(loginDTO.getEmail());

        boolean isPassOK = UtilGeneral.desencriptar(loginDTO.getPassword(), usuario.getPassword());

        if (!isPassOK) {
            throw new BombappException(CodigoRespuestaApi.CREDENTIALS_INCORRECT);
        }

        UsuarioDTO usuarioDTO = mapDTOAndGenerateToken(usuario);
        usuarioDTO.setListPermissions(permisoService.getListByRol(usuario.getRoles().getId()));

        return usuarioDTO;
    }

    /**
     * Mapea al DTO y genera el token
     *
     * @param usuarios Usuarios
     * @return UsuarioDTO
     */
    private UsuarioDTO mapDTOAndGenerateToken(Usuarios usuarios) {
        String token = jwtTokenUtil.generateAccessToken(usuarios);

        UsuarioDTO usuarioDTO = (UsuarioDTO) utilService.mapDTO(usuarios, new UsuarioDTO());
        usuarioDTO.setToken(token);

        return usuarioDTO;
    }
}
