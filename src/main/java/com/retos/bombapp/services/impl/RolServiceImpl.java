package com.retos.bombapp.services.impl;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.entities.Roles;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.models.RolDTO;
import com.retos.bombapp.repositories.RolRepository;
import com.retos.bombapp.services.RolService;
import com.retos.bombapp.services.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Servicio para roles
 */
@Service
public class RolServiceImpl implements RolService {
    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UtilService utilService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Roles insertRol(RolDTO rolDTO) {
        rolDTO.setId(null);
        Roles roles = (Roles) utilService.mapDTO(rolDTO, new Roles());
        roles.setVisibility(true);

        Roles rolesInsert;
        try {
            rolesInsert = rolRepository.save(roles);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_INSERT_ROL);
        }

        return rolesInsert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Roles updateRol(Long id, RolDTO rolDTO) {
        Roles roles = rolRepository.findById(id).orElse(null);

        if (Objects.isNull(roles)) {
            throw new BombappException(CodigoRespuestaApi.ROL_NOT_FOUND);
        }

        Roles rolesMap = (Roles) utilService.mapDTO(rolDTO, new Roles());
        rolesMap.setId(id);

        Roles rolesUpdate;
        try {
            rolesUpdate = rolRepository.save(rolesMap);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_UPDATE_ROL);
        }

        return rolesUpdate;
    }
}
