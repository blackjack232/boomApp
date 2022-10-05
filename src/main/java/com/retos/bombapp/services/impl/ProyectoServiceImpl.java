package com.retos.bombapp.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.constants.ConstantesParametros;
import com.retos.bombapp.entities.Asignaciones;
import com.retos.bombapp.entities.Clientes;
import com.retos.bombapp.entities.Parametros;
import com.retos.bombapp.entities.Proyectos;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.models.ProyectoDTO;
import com.retos.bombapp.models.TokenDTO;
import com.retos.bombapp.repositories.AsignacionRepository;
import com.retos.bombapp.repositories.EtapaRepository;
import com.retos.bombapp.repositories.ParametroRepository;
import com.retos.bombapp.repositories.ProyectoRepository;
import com.retos.bombapp.services.ProyectoService;
import com.retos.bombapp.services.UtilService;
import com.retos.bombapp.utils.UtilGeneral;

/**
 * Servicio para proyectos
 */
@Service
public class ProyectoServiceImpl implements ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private ParametroRepository parametroRepository;

    @Autowired
    private AsignacionRepository asignacionRepository;

    @Autowired
    private EtapaRepository etapaRepository;

    @Autowired
    private UtilService utilService;

    private List<Parametros> listParameters;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoDTO> getAllProjectsByRol(String token) {
        TokenDTO tokenDTO = utilService.decodePayloadToMapDTO(token);

        Long userId = tokenDTO.getUserId();
        boolean isArtist = UtilGeneral.isArtist(tokenDTO.getRolCode());

        this.listParameters = parametroRepository.findByType(ConstantesParametros.TYPE_STATUS_PROJECT);

        List<Proyectos> listProjects = isArtist ? proyectoRepository.getAllByUserAndStatus(userId, Constantes.ACTIVE)
                : proyectoRepository.findAllByStatus(Constantes.ACTIVE);

        return listProjects.stream().map(p -> assigStatus(p, userId, isArtist)).toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoDTO getById(Long id) {
        Proyectos p = proyectoRepository.findById(id).orElse(null);

        if (Objects.isNull(p)) {
            throw new BombappException(CodigoRespuestaApi.PROJECT_NOT_FOUND);
        }

        ProyectoDTO proDTO = mapDTO(p);

        Proyectos proExists = Proyectos.builder().id(id).build();

        proDTO.setHasAssignments(asignacionRepository.existsByProyectos(proExists));
        proDTO.setHasStages(etapaRepository.existsByProyectos(proExists));

        return proDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos insertProject(ProyectoDTO proyectoDTO) {
        proyectoDTO.setId(null);
        Proyectos proyectos = (Proyectos) utilService.mapDTO(proyectoDTO, new Proyectos());
        proyectos.setStartDate(UtilGeneral.convertStringToDate(proyectoDTO.getStartDate(), Constantes.FORMAT_DATE_ONE));
        proyectos.setEndDate(UtilGeneral.convertStringToDate(proyectoDTO.getEndDate(), Constantes.FORMAT_DATE_ONE));
        proyectos.setStatus(Constantes.ACTIVE);
        if (Objects.nonNull(proyectoDTO.getClientId())) {
            proyectos.setClientes(Clientes.builder().id(proyectoDTO.getClientId()).build());
        }

        Proyectos proyectosInsert;
        try {
            proyectosInsert = proyectoRepository.save(proyectos);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_INSERT_PROJECT);
        }

        return proyectosInsert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos updateProject(Long id, ProyectoDTO proyectoDTO) {
        Proyectos pro = proyectoRepository.findById(id).orElse(null);

        if (Objects.isNull(pro)) {
            throw new BombappException(CodigoRespuestaApi.PROJECT_NOT_FOUND);
        }

        Proyectos proyectos = (Proyectos) utilService.mapDTO(proyectoDTO, new Proyectos());
        proyectos.setId(id);
        proyectos.setStartDate(UtilGeneral.convertStringToDate(proyectoDTO.getStartDate(), Constantes.FORMAT_DATE_ONE));
        proyectos.setEndDate(UtilGeneral.convertStringToDate(proyectoDTO.getEndDate(), Constantes.FORMAT_DATE_ONE));
        if (Objects.nonNull(proyectoDTO.getClientId())) {
            proyectos.setClientes(Clientes.builder().id(proyectoDTO.getClientId()).build());
        }

        Proyectos proyectosUpdate;
        try {
            proyectosUpdate = proyectoRepository.save(proyectos);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_UPDATE_PROJECT);
        }

        return proyectosUpdate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteProject(Long id) {
        Proyectos p = proyectoRepository.findById(id).orElse(null);

        if (Objects.isNull(p)) {
            throw new BombappException(CodigoRespuestaApi.PROJECT_NOT_FOUND);
        }

        try {
            p.setStatus(Constantes.INACTIVE);
            proyectoRepository.save(p);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_DELETE_PROJECT);
        }
    }

    // --------------------- PRIVADOS --------------------------
    /**
     * Filtra y asigna el estado del proyecto
     *
     * @param p Proyectos
     * @return ProyectoDTO
     */
    private ProyectoDTO assigStatus(Proyectos p, Long idUser, boolean flag) {
        ProyectoDTO proDTO = mapDTO(p);

        List<Asignaciones> listAssignments;
        if (flag) {
            listAssignments = asignacionRepository.getAllByProjectAndUser(p.getId(), idUser);
        } else {
            listAssignments = asignacionRepository
                    .findAllByProyectosAndStatusOrderByIdDesc(
                            Proyectos.builder().id(p.getId()).build(),
                            Constantes.ACTIVE);
        }

        setStatusProjectAssig(listAssignments, proDTO);

        return proDTO;
    }

    /**
     * Mapea el DTO
     *
     * @param p Proyectos
     * @return ProyectoDTO
     */
    private ProyectoDTO mapDTO(Proyectos p) {
        ProyectoDTO proDTO = (ProyectoDTO) utilService.mapDTO(p, new ProyectoDTO());
        proDTO.setStartDate(UtilGeneral.convertDateToString(p.getStartDate(), Constantes.FORMAT_DATE_ONE));
        proDTO.setEndDate(UtilGeneral.convertDateToString(p.getEndDate(), Constantes.FORMAT_DATE_ONE));
        proDTO.setClientId(Objects.nonNull(p.getClientes()) ? p.getClientes().getId() : null);

        return proDTO;
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

    /**
     * Asigna un estado al estado del proyecto cumpliendo unos requisitos
     *
     * @param listAssignments List<Asignaciones>
     * @param proDTO          ProyectoDTO
     */
    private void setStatusProjectAssig(List<Asignaciones> listAssignments, ProyectoDTO proDTO) {
        if (listAssignments.isEmpty()) {
            proDTO.setStatus(Constantes.STATUS_ASSIGNMENTLESS);
            return;
        }

        Date currentDate = new Date();
        Date dateProject = UtilGeneral.convertStringToDate(proDTO.getEndDate(), Constantes.FORMAT_DATE_ONE);

        Predicate<Asignaciones> isExpired = p -> (currentDate.after(dateProject)
                && !Objects.equals(searchNameParameter(p.getStatusId()), Constantes.STATUS_ASSIGNMENT_COMPLETED))
                || currentDate.after(p.getExpectedEndDate())
                || (Objects.nonNull(p.getActualEndDate()) && p.getActualEndDate().after(p.getExpectedEndDate()));

        Predicate<Asignaciones> isComplete = p -> Objects.equals(searchNameParameter(p.getStatusId()),
                Constantes.STATUS_ASSIGNMENT_COMPLETED);

        // Si al menos una ya cumplió la fecha de finalización del proyecto y no se han
        // terminado todas las tareas
        if (listAssignments.stream().anyMatch(isExpired)) {
            proDTO.setStatus(Constantes.STATUS_PROJECT_EXPIRED);
            return;
        }

        // Si ya se han terminado todas las tareas
        if (listAssignments.stream().allMatch(isComplete)) {
            proDTO.setStatus(Constantes.STATUS_PROJECT_COMPLETED);
            return;
        }

        // Si no, porque está en proceso
        proDTO.setStatus(Constantes.STATUS_PROJECT_PROCESS);
    }
}
