package com.retos.bombapp.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.entities.Parametros;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.models.ParametroDTO;
import com.retos.bombapp.repositories.ParametroRepository;
import com.retos.bombapp.services.ParametroService;
import com.retos.bombapp.services.UtilService;
import com.retos.bombapp.utils.UtilGeneral;

/**
 * Servicio de parámetros
 */
@Service
public class ParametroServiceImpl implements ParametroService {
    @Autowired
    private ParametroRepository parametroRepository;

    @Autowired
    private UtilService utilService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ParametroDTO> getAllOnlyTypes() {
        List<Parametros> parametros = parametroRepository.findAllByStatus(Constantes.ACTIVE);
        return parametros.stream()
                .filter(UtilGeneral.distinctByKey(Parametros::getType))
                .map(p -> ParametroDTO
                        .builder()
                        .id(p.getId())
                        .type(p.getType())
                        .build())
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ParametroDTO> getAllByType(String type) {
        List<Parametros> listParametros = parametroRepository.findByType(type);
        return listParametros.stream()
                .sorted(Comparator.comparing(Parametros::getOrder))
                .map(p -> (ParametroDTO) utilService.mapDTO(p, new ParametroDTO()))
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Parametros insertParameter(ParametroDTO parametroDTO) {
        Parametros p = parametroRepository.findByTypeAndNameIgnoreCase(parametroDTO.getType(), parametroDTO.getName()).orElse(null);
        
        if (Objects.nonNull(p)) {
            throw new BombappException(CodigoRespuestaApi.PARAMETER_EXISTS);
        }
        
        Parametros parametros = (Parametros) utilService.mapDTO(parametroDTO, new Parametros());
        parametros.setStatus(Constantes.ACTIVE);
        parametros.setName(parametros.getName().toUpperCase());

        List<Parametros> listParams = parametroRepository.findByType(parametros.getType());
        parametros.setOrder((short) (listParams.size() + 1));
        parametros.setCode(UtilGeneral.generateCodeParameter(parametros.getType(), parametros.getName(), parametros.getOrder(), "_"));

        Parametros parametrosInsert;
        try {
            parametrosInsert = parametroRepository.save(parametros);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_INSERT_PARAMETER);
        }

        return parametrosInsert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Parametros updateParameter(Long id, ParametroDTO parametroDTO) {
        Parametros parametros = parametroRepository.findById(id).orElse(null);

        if (Objects.isNull(parametros)) {
            throw new BombappException(CodigoRespuestaApi.PARAMETER_NOT_FOUND);
        }
        
        Parametros parametrosMap = (Parametros) utilService.mapDTO(parametroDTO, new Parametros());
        parametrosMap.setId(id);
        parametrosMap.setName(parametrosMap.getName().toUpperCase());
        parametrosMap.setCode(UtilGeneral.generateCodeParameter(parametrosMap.getType(), parametrosMap.getName(), parametros.getOrder(), "_"));
        parametrosMap.setOrder(parametros.getOrder());

        Parametros parametrosUpdate;
        try {
            parametrosUpdate = parametroRepository.save(parametrosMap);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_UPDATE_PARAMETER);
        }

        return parametrosUpdate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeStatus(Long id, String status) {
        Parametros parametros = parametroRepository.findById(id).orElse(null);

        if (Objects.isNull(parametros)) {
            throw new BombappException(CodigoRespuestaApi.PARAMETER_NOT_FOUND);
        }

        if (status.equalsIgnoreCase(parametros.getStatus()))
            return;

        parametros.setStatus(status);
        try {
            parametroRepository.save(parametros);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.PARAMETER_CHANGE_STATUS_ERROR);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeOrder(Long id, boolean order) {
        Parametros parametros = parametroRepository.findById(id).orElse(null);

        if (Objects.isNull(parametros)) {
            throw new BombappException(CodigoRespuestaApi.PARAMETER_NOT_FOUND);
        }

        boolean first = parametros.getOrder() == 1;
        if (first && order)
            return;

        List<Parametros> listParams = parametroRepository.findByType(parametros.getType());

        boolean last = parametros.getOrder() == listParams.size();
        if (last && !order)
            return;

        parametros.setOrder((short) (order ? parametros.getOrder() - 1 : parametros.getOrder() + 1));

        Parametros secondParametros = listParams.stream()
                .filter(p -> p.getOrder() == parametros.getOrder())
                .findFirst()
                .orElse(null);

        List<Parametros> listParameter = new ArrayList<>();
        listParameter.add(parametros);
        if (Objects.nonNull(secondParametros)) {
            secondParametros.setOrder((short) (!order ? secondParametros.getOrder() - 1 : secondParametros.getOrder() + 1));
            listParameter.add(secondParametros);
        }

        try {
            parametroRepository.saveAll(listParameter);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.PARAMETER_CHANGE_ORDER_ERROR);
        }
    }
}
