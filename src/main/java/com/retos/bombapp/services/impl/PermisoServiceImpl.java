package com.retos.bombapp.services.impl;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.entities.Funcionalidades;
import com.retos.bombapp.entities.Permisos;
import com.retos.bombapp.entities.Roles;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.models.FuncionalidadPermisoDTO;
import com.retos.bombapp.models.PermisoDTO;
import com.retos.bombapp.repositories.FuncionalidadRepository;
import com.retos.bombapp.repositories.PermisoRepository;
import com.retos.bombapp.services.PermisoService;
import com.retos.bombapp.services.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Servicio para permisos
 */
@Service
public class PermisoServiceImpl implements PermisoService {
    @Autowired
    private UtilService utilService;

    @Autowired
    private PermisoRepository permisoRepository;

    @Autowired
    private FuncionalidadRepository funcionalidadRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateByRolPermission(FuncionalidadPermisoDTO funcionalidadPermisoDTO) {
        Long rolId = funcionalidadPermisoDTO.getRolId();
        List<PermisoDTO> listFunctionalies = funcionalidadPermisoDTO.getList();

        List<Permisos> listPermisos = new ArrayList<>();
        for (PermisoDTO obj : listFunctionalies) {
            Permisos permisos = (Permisos) utilService.mapDTO(obj, new Permisos());
            permisos.setRoles(Roles.builder().id(rolId).build());
            permisos.setFuncionalidades(Funcionalidades.builder().id(obj.getFunctionalityId()).build());
            listPermisos.add(permisos);
        }

        try {
            permisoRepository.saveAll(listPermisos);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_INSERT_PERMISSION);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public FuncionalidadPermisoDTO getAllByRol(Long rol) {
        List<PermisoDTO> listPermisoDTO = getListByRol(rol);

        FuncionalidadPermisoDTO funcionalidadPermisoDTO = new FuncionalidadPermisoDTO();
        funcionalidadPermisoDTO.setRolId(rol);
        funcionalidadPermisoDTO.setList(listPermisoDTO);

        return funcionalidadPermisoDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PermisoDTO> getListByRol(Long rol) {
        List<Funcionalidades> listFuncionalidades = funcionalidadRepository.findAll();
        List<Permisos> listPermisos = permisoRepository.findByRoles(Roles.builder().id(rol).build());

        List<PermisoDTO> listPermisoDTO = new ArrayList<>();

        for (Funcionalidades f : listFuncionalidades) {
            Optional<Permisos> permisos = listPermisos.stream()
                    .filter(p -> Objects.equals(p.getFuncionalidades().getId(), f.getId()))
                    .findFirst();

            PermisoDTO permisoDTO;
            if (permisos.isPresent()) {
                permisoDTO = (PermisoDTO) utilService.mapDTO(permisos.get(), new PermisoDTO());
                permisoDTO.setFunctionalityId(f.getId());
                permisoDTO.setFunctionalityName(f.getName());
                permisoDTO.setFunctionalityCode(f.getCode());
            } else {
                permisoDTO = PermisoDTO
                        .builder()
                        .functionalityId(f.getId())
                        .functionalityName(f.getName())
                        .functionalityCode(f.getCode())
                        .consultAction(false)
                        .createAction(false)
                        .deleteAction(false)
                        .updateAction(false)
                        .build();
            }
            listPermisoDTO.add(permisoDTO);
        }

        return listPermisoDTO;
    }
}
