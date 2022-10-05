package com.retos.bombapp.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.constants.ConstantesParametros;
import com.retos.bombapp.entities.Asignaciones;
import com.retos.bombapp.entities.Parametros;
import com.retos.bombapp.entities.Proyectos;
import com.retos.bombapp.entities.UsuariosAsignaciones;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.models.AsignacionDTO;
import com.retos.bombapp.models.TokenDTO;
import com.retos.bombapp.repositories.AsignacionRepository;
import com.retos.bombapp.repositories.ParametroRepository;
import com.retos.bombapp.repositories.UsuarioAsignacionRepository;
import com.retos.bombapp.services.AsignacionService;
import com.retos.bombapp.services.UsuarioAsignacionService;
import com.retos.bombapp.services.UtilService;
import com.retos.bombapp.utils.UtilGeneral;

/**
 * Servicio de asignaciones
 */
@Service
public class AsignacionServiceImpl implements AsignacionService {

    @Autowired
    private AsignacionRepository asignacionRepository;

    @Autowired
    private UtilService utilService;

    @Autowired
    private UsuarioAsignacionService usuarioAsignacionService;

    @Autowired
    private UsuarioAsignacionRepository usuarioAsignacionRepository;

    @Autowired
    private ParametroRepository parametroRepository;

    private List<Parametros> listParameters;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AsignacionDTO> getAllByProject(Long id, String token) {
        TokenDTO tokenDTO = utilService.decodePayloadToMapDTO(token);

        Long userId = tokenDTO.getUserId();
        boolean isArtist = UtilGeneral.isArtist(tokenDTO.getRolCode());

        List<Asignaciones> listAssignments = isArtist ? asignacionRepository.getAllByProjectAndUser(id, userId)
                : asignacionRepository.findAllByProyectosAndStatusOrderByIdDesc(
                        Proyectos.builder().id(id).build(),
                        Constantes.ACTIVE);

        this.listParameters = parametroRepository.findByType(ConstantesParametros.TYPE_STATUS_ASSIGNMENT);

        List<AsignacionDTO> listAssDTO = new ArrayList<>();
        for (Asignaciones ass : listAssignments) {
            AsignacionDTO asignacionDTO = mapDTO(ass);

            List<UsuariosAsignaciones> list = usuarioAsignacionRepository.findAllByAsignaciones(
                    Asignaciones.builder().id(asignacionDTO.getId()).build());
            asignacionDTO.setUsers(list.stream().map(u -> u.getUsuarios().getId()).toList());

            listAssDTO.add(asignacionDTO);
        }
        return listAssDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AsignacionDTO getById(Long id) {
        Asignaciones asignaciones = asignacionRepository.findById(id).orElse(null);

        if (Objects.isNull(asignaciones)) {
            throw new BombappException(CodigoRespuestaApi.ASSIGNMENT_NOT_FOUND);
        }

        AsignacionDTO asignacionDTO = mapDTO(asignaciones);

        if (Objects.nonNull(asignaciones.getProyectos())) {
            asignacionDTO.setProjectId(asignaciones.getProyectos().getId());
        }

        List<UsuariosAsignaciones> list = usuarioAsignacionRepository.findAllByAsignaciones(
                Asignaciones.builder().id(asignacionDTO.getId()).build());
        asignacionDTO.setUsers(list.stream().map(u -> u.getUsuarios().getId()).toList());

        return asignacionDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Asignaciones insertAssignment(AsignacionDTO asignacionDTO) {
        asignacionDTO.setId(null);

        if (Objects.isNull(asignacionDTO.getProjectId())) {
            throw new BombappException(CodigoRespuestaApi.NO_PROJECT_ASSIGNMENT);
        }

        Asignaciones asignaciones = mapAssig(asignacionDTO);
        asignaciones.setStatus(Constantes.ACTIVE);

        Asignaciones asignacionesInsert;
        try {
            asignacionesInsert = asignacionRepository.save(asignaciones);

            if (Objects.nonNull(asignacionDTO.getUsers()) && Objects.nonNull(asignacionesInsert.getId())) {
                usuarioAsignacionService.insertAll(
                        asignacionesInsert.getId(), asignacionDTO.getUsers());
            }
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_INSERT_ASSIGNMENT);
        }

        return asignacionesInsert;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Asignaciones updateAssignment(Long id, AsignacionDTO asignacionDTO) {
        asignacionDTO.setId(null);

        if (Objects.isNull(asignacionDTO.getProjectId())) {
            throw new BombappException(CodigoRespuestaApi.NO_PROJECT_ASSIGNMENT);
        }

        Asignaciones asignaciones = mapAssig(asignacionDTO);
        asignaciones.setId(id);

        Asignaciones asignacionesUpdate;
        try {
            asignacionesUpdate = asignacionRepository.save(asignaciones);

            if (Objects.nonNull(asignacionDTO.getUsers()) && Objects.nonNull(asignacionesUpdate.getId())) {
                usuarioAsignacionService.deleteAndInsertAllByAsignacion(
                        asignacionesUpdate.getId(), asignacionDTO.getUsers());
            }
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_UPDATE_ASSIGNMENT);
        }

        return asignacionesUpdate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAssignment(Long id) {
        Asignaciones ass = asignacionRepository.findById(id).orElse(null);

        if (Objects.isNull(ass)) {
            throw new BombappException(CodigoRespuestaApi.ASSIGNMENT_NOT_FOUND);
        }

        try {
            ass.setStatus(Constantes.INACTIVE);
            asignacionRepository.save(ass);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_DELETE_ASSIGNMENT);
        }
    }

    // --------------------------------PRIVADOS----------------------------------------
    /**
     * Mapea el DTO de Asignaciones a AsignacionDTO
     *
     * @param ass Asignaciones
     * @return AsignacionDTO
     */
    private AsignacionDTO mapDTO(Asignaciones ass) {
        Date currentDate = new Date();
        String nameStatus = searchNameParameter(ass.getStatusId());

        AsignacionDTO asignacionDTO = (AsignacionDTO) utilService.mapDTO(ass, new AsignacionDTO());
        asignacionDTO.setProjectId(ass.getProyectos().getId());
        asignacionDTO.setAssignmentDate(
                UtilGeneral.convertDateToString(ass.getAssignmentDate(), Constantes.FORMAT_DATE_ONE));
        asignacionDTO.setExpectedStartDate(
                UtilGeneral.convertDateToString(ass.getExpectedStartDate(), Constantes.FORMAT_DATE_ONE));
        asignacionDTO.setExpectedEndDate(
                UtilGeneral.convertDateToString(ass.getExpectedEndDate(), Constantes.FORMAT_DATE_ONE));
        asignacionDTO.setActualStartDate(
                UtilGeneral.convertDateToString(ass.getActualStartDate(), Constantes.FORMAT_DATE_ONE));
        asignacionDTO
                .setActualEndDate(UtilGeneral.convertDateToString(ass.getActualEndDate(), Constantes.FORMAT_DATE_ONE));
        asignacionDTO.setExpired(currentDate.after(ass.getExpectedEndDate())
                && !Objects.equals(nameStatus, Constantes.STATUS_ASSIGNMENT_COMPLETED));
        return asignacionDTO;
    }

    /**
     * Mapea el DTO de AsignacionDTO a Asignaciones
     *
     * @param asignacionDTO AsignacionDTO
     * @return Asignaciones
     */
    private Asignaciones mapAssig(AsignacionDTO asignacionDTO) {
        Asignaciones asignaciones = (Asignaciones) utilService.mapDTO(asignacionDTO, new Asignaciones());
        asignaciones.setProyectos(Proyectos.builder().id(asignacionDTO.getProjectId()).build());
        asignaciones.setAssignmentDate(
                UtilGeneral.convertStringToDate(asignacionDTO.getAssignmentDate(), Constantes.FORMAT_DATE_ONE));
        asignaciones.setExpectedStartDate(
                UtilGeneral.convertStringToDate(asignacionDTO.getExpectedStartDate(), Constantes.FORMAT_DATE_ONE));
        asignaciones.setExpectedEndDate(
                UtilGeneral.convertStringToDate(asignacionDTO.getExpectedEndDate(), Constantes.FORMAT_DATE_ONE));
        asignaciones.setActualStartDate(
                UtilGeneral.convertStringToDate(asignacionDTO.getActualStartDate(), Constantes.FORMAT_DATE_ONE));
        asignaciones.setActualEndDate(
                UtilGeneral.convertStringToDate(asignacionDTO.getActualEndDate(), Constantes.FORMAT_DATE_ONE));
        return asignaciones;
    }

    /**
     * Busca el nombre del parámetro por el id del estado
     *
     * @param statusId Long
     * @return String
     */
    private String searchNameParameter(Long statusId) {
        if (Objects.isNull(this.listParameters)) {
            return null;
        }

        Parametros param = this.listParameters.stream()
                .filter(p -> Objects.equals(p.getId(), statusId))
                .findFirst().orElse(null);

        return Objects.nonNull(param) ? param.getName() : null;
    }
}
