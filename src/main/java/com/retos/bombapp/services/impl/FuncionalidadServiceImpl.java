package com.retos.bombapp.services.impl;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.entities.Funcionalidades;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.models.FuncionalidadDTO;
import com.retos.bombapp.repositories.FuncionalidadRepository;
import com.retos.bombapp.services.FuncionalidadService;
import com.retos.bombapp.services.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio para funcionalidades
 */
@Service
public class FuncionalidadServiceImpl implements FuncionalidadService {
    @Autowired
    private FuncionalidadRepository funcionalidadRepository;

    @Autowired
    private UtilService utilService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Funcionalidades insertFunctionality(FuncionalidadDTO funcionalidadDTO) {
        funcionalidadDTO.setId(null);
        Funcionalidades funcionalidades = (Funcionalidades) utilService.mapDTO(funcionalidadDTO, new Funcionalidades());

        Funcionalidades funcionalidadesInsert;
        try {
            funcionalidadesInsert = funcionalidadRepository.save(funcionalidades);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_INSERT_FUNCTIONALITY);
        }

        return funcionalidadesInsert;
    }
}
