package com.retos.bombapp.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.entities.Asignaciones;
import com.retos.bombapp.entities.Usuarios;
import com.retos.bombapp.entities.UsuariosAsignaciones;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.repositories.UsuarioAsignacionRepository;
import com.retos.bombapp.services.UsuarioAsignacionService;

/**
 * Servicio de usuarios asignaciones
 */
@Service
public class UsuarioAsignacionServiceImpl implements UsuarioAsignacionService {
    @Autowired
    private UsuarioAsignacionRepository usuarioAsignacionRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void insertAll(Long asignacion, List<Long> listUsuarioAsignacion) {
        List<UsuariosAsignaciones> list = new ArrayList<>();
        for (Long id : listUsuarioAsignacion) {
            list.add(UsuariosAsignaciones
                    .builder()
                    .asignaciones(Asignaciones.builder().id(asignacion).build())
                    .usuarios(Usuarios.builder().id(id).build())
                    .build());
        }

        try {
            usuarioAsignacionRepository.saveAll(list);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_INSERT_USER_ASSIGNMENT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteAllByAsignacion(Long id) {
        try {
            usuarioAsignacionRepository.deleteAllByAsignaciones(Asignaciones.builder().id(id).build());
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_DELETE_USER_ASSIGNMENT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteAndInsertAllByAsignacion(Long asignacion, List<Long> listUsuarioAsignacion) {
        deleteAllByAsignacion(asignacion);
        insertAll(asignacion, listUsuarioAsignacion);
    }
}
