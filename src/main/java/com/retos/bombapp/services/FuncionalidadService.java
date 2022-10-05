package com.retos.bombapp.services;

import com.retos.bombapp.entities.Funcionalidades;
import com.retos.bombapp.models.FuncionalidadDTO;

public interface FuncionalidadService {
    /**
     * Inserta la funcionalidad a la base de datos
     * @param funcionalidadDTO FuncionalidadDTO
     * @return Funcionalidades
     */
    Funcionalidades insertFunctionality(FuncionalidadDTO funcionalidadDTO);
}
