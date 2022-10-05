package com.retos.bombapp.services;

import com.retos.bombapp.entities.Usuarios;
import com.retos.bombapp.models.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    /**
     * Inserta el usuario en la base de datos
     * @param usuarioDTO UsuarioDTO
     * @return Usuarios
     */
    Usuarios insertUser(UsuarioDTO usuarioDTO);

    /**
     * Obtiene todos los usuarios y su cargo
     * @return List<UsuarioDTO>
     */
    List<UsuarioDTO> getAllUsuariosYCargo();

    /**
     * Obtiene el usuario y su cargo por id
     * @param id Long
     * @return UsuarioDTO
     */
    UsuarioDTO getByIdUsuariosYCargo(Long id);

    /**
     * Elimina el usuario en la base de datos
     * @param id Long
     */
    void deleteUser(Long id);

    /**
     * Recupera la contraseña del usuario
     * @param email String
     */
    void recoverPassword(String email);

    /**
     * Actualizar el usuario
     * @param id Long
     * @param usuarioDTO UsuarioDTO
     * @return Usuarios
     */
    Usuarios updateUser(Long id, UsuarioDTO usuarioDTO);

    /**
     * Cambia la contraseña del usuario
     * @param id Long
     * @param password String
     */
    void changePassword(Long id, String password);

    /**
     * Cambia el estado del usuario
     * @param id Long
     * @param status String
     */
    void changeStatus(Long id, String status);
}
