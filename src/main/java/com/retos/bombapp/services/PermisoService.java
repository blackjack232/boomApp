package com.retos.bombapp.services;

import com.retos.bombapp.models.FuncionalidadPermisoDTO;
import com.retos.bombapp.models.PermisoDTO;

import java.util.List;

public interface PermisoService {
    /**
     * Actualiza los permisos en la base de datos
     * @param funcionalidadPermisoDTO FuncionalidadPermisoDTO
     */
    void updateByRolPermission(FuncionalidadPermisoDTO funcionalidadPermisoDTO);

    /**
     * Obtiene los permisos por rol
     * @param rol Long
     * @return FuncionalidadPermisoDTO
     */
    FuncionalidadPermisoDTO getAllByRol(Long rol);

    /**
     * Obtiene la lista de los permisos por rol
     * @param rol Long
     * @return List<PermisoDTO>
     */
    List<PermisoDTO> getListByRol(Long rol);
}
