package com.retos.bombapp.services;

import com.retos.bombapp.entities.UsuariosCargos;

import java.util.List;

public interface UsuarioCargoService {
    /**
     * Eliminar el usuarioCargo por el usuario de la base de datos
     * @param id Long
     */
    void deleteAllByUsuarios(Long id);

    /**
     * Inserta el usuarioCargo a la base de datos
     * @param list List<UsuariosCargos>
     */
    void insertAllUsuariosCargos(List<UsuariosCargos> list);
}
