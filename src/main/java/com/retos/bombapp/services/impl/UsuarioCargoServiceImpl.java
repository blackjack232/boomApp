package com.retos.bombapp.services.impl;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.entities.Usuarios;
import com.retos.bombapp.entities.UsuariosCargos;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.repositories.UsuarioCargoRepository;
import com.retos.bombapp.services.UsuarioCargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio de Usuario Cargo
 */
@Service
public class UsuarioCargoServiceImpl implements UsuarioCargoService {
    @Autowired
    private UsuarioCargoRepository usuarioCargoRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteAllByUsuarios(Long id) {
        Usuarios usuarios = Usuarios
                .builder()
                .id(id)
                .build();
        try {
            usuarioCargoRepository.deleteByUsuarios(usuarios);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_DELETE_USER_POSITION);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void insertAllUsuariosCargos(List<UsuariosCargos> list) {
        try {
            usuarioCargoRepository.saveAll(list);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_INSERT_USER_POSITION);
        }
    }
}
