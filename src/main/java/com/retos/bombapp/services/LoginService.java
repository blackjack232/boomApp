package com.retos.bombapp.services;

import com.retos.bombapp.models.LoginDTO;
import com.retos.bombapp.models.UsuarioDTO;

public interface LoginService {
    /**
     * Autentica el usuario y la contraseña
     * @param loginDTO LoginDTO
     * @return UsuarioDTO
     */
    UsuarioDTO authenticate(LoginDTO loginDTO);
}
