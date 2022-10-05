package com.retos.bombapp.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.ConstantesParametros;
import com.retos.bombapp.entities.Etapas;
import com.retos.bombapp.entities.Parametros;
import com.retos.bombapp.entities.Proyectos;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.models.EtapaDTO;
import com.retos.bombapp.repositories.EtapaRepository;
import com.retos.bombapp.repositories.ParametroRepository;
import com.retos.bombapp.repositories.ProyectoRepository;
import com.retos.bombapp.services.EtapaService;
import com.retos.bombapp.services.UtilService;

/**
 * Servicios para las etapas
 */
@Service
public class EtapaServiceImpl implements EtapaService {
    @Autowired
    private EtapaRepository etapaRepository;

    @Autowired
    private UtilService utilService;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private ParametroRepository parametroRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EtapaDTO> getAllByProject(Long idProject) {
        List<Etapas> listStages = etapaRepository.findAllByProyectos(Proyectos.builder().id(idProject).build());

        List<Parametros> listParameters = parametroRepository.findByType(ConstantesParametros.TYPE_STAGE);

        List<EtapaDTO> listStagesDTO = new ArrayList<>();
        for (Etapas e : listStages) {
            EtapaDTO etapaDTO = (EtapaDTO) utilService.mapDTO(e, new EtapaDTO());
            etapaDTO.setStageParameterName(getNameParameter(listParameters, e.getStageParameterId()));
            listStagesDTO.add(etapaDTO);
        }

        return listStagesDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void insertStage(EtapaDTO etapaDTO) {
        List<Long> parametersId = etapaDTO.getParametersStageId();
        Long idProject = etapaDTO.getProjectId();

        if (Objects.isNull(parametersId) || Objects.isNull(idProject)) {
            throw new BombappException(CodigoRespuestaApi.STAGE_INSERT_EMPTY);
        }

        Proyectos proyectos = proyectoRepository.findById(idProject).orElse(null);
        if (Objects.isNull(proyectos)) {
            throw new BombappException(CodigoRespuestaApi.PROJECT_NOT_FOUND);
        }

        deleteAll(idProject);

        List<Etapas> stages = new ArrayList<>();
        for (Long p : parametersId) {
            stages.add(
                    Etapas.builder()
                            .stageParameterId(p)
                            .proyectos(Proyectos.builder().id(idProject).build())
                            .build());
        }

        insertAll(stages);
    }

    /**
     * Elimina todas las etapas por el proyecto
     * 
     * @param id Long
     */
    @Transactional
    public void deleteAll(Long id) {
        try {
            etapaRepository.deleteAllByProyectos(Proyectos.builder().id(id).build());
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_DELETE_STAGE);
        }
    }

    /**
     * Inserta una lista de etapas
     * 
     * @param stages List<Etapas>
     */
    @Transactional
    public void insertAll(List<Etapas> stages) {
        try {
            etapaRepository.saveAll(stages);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_INSERT_STAGE);
        }
    }

    // ---------------------------------- PRIVADOS ---------------------------------
    /**
     * Obtiene el nombre del parámetros por id
     * 
     * @param listParameters List<Parametros>
     * @param id             Long
     * @return String
     */
    private String getNameParameter(List<Parametros> listParameters, Long id) {
        Parametros param = listParameters.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst().orElse(null);
        return Objects.nonNull(param) ? param.getName() : null;
    }
}
