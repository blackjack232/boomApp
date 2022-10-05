package com.retos.bombapp.services;

import com.retos.bombapp.entities.Roles;
import com.retos.bombapp.models.RolDTO;

public interface RolService {
    /**
     * Inserta el rol en la base de datos
     * @param rolDTO RolDTO
     * @return Roles
     */
    Roles insertRol(RolDTO rolDTO);

    /**
     * Actualiza el rol en la base de datos
     * @param id Long
     * @param rolDTO RolDTO
     * @return Roles
     */
    Roles updateRol(Long id, RolDTO rolDTO);
}
