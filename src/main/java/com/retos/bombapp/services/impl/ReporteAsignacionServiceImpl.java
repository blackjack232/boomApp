package com.retos.bombapp.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.constants.ConstantesParametros;
import com.retos.bombapp.entities.Parametros;
import com.retos.bombapp.entities.UsuariosAsignaciones;
import com.retos.bombapp.enums.AsignacionesReporteEnum;
import com.retos.bombapp.enums.OrdenamientoEnum;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.models.ReporteAsignacionDTO;
import com.retos.bombapp.repositories.ParametroRepository;
import com.retos.bombapp.repositories.UsuarioAsignacionRepository;
import com.retos.bombapp.services.ReporteAsignacionService;
import com.retos.bombapp.utils.UtilGeneral;

/**
 * Servicio para reportes de asignación
 */
@Service
public class ReporteAsignacionServiceImpl implements ReporteAsignacionService {
    @Autowired
    private UsuarioAsignacionRepository usuarioAsignacionRepository;

    @Autowired
    private ParametroRepository parametroRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ReporteAsignacionDTO> filterAllReportByProject(ReporteAsignacionDTO reporteAsignacionDTO) {
        if (Objects.isNull(reporteAsignacionDTO.getProjectId()) || Objects.isNull(reporteAsignacionDTO.getField())
                || Objects.isNull(reporteAsignacionDTO.getStageId())) {
            throw new BombappException(CodigoRespuestaApi.NO_DATA_REPORT_ASSIGNMENT);
        }

        List<UsuariosAsignaciones> listAssigUser = usuarioAsignacionRepository
                .getAllByProjectAndStage(reporteAsignacionDTO.getProjectId(), reporteAsignacionDTO.getStageId());

        List<Parametros> listParameter = parametroRepository.findByType(ConstantesParametros.TYPE_STATUS_ASSIGNMENT);

        List<ReporteAsignacionDTO> list = new ArrayList<>();
        for (UsuariosAsignaciones ua : listAssigUser) {
            list.add(
                    ReporteAsignacionDTO
                            .builder()
                            .responsible(ua.getUsuarios().getNames() + " " + ua.getUsuarios().getLastnames())
                            .task(ua.getAsignaciones().getName())
                            .startDateD(ua.getAsignaciones().getExpectedStartDate())
                            .endDateD(ua.getAsignaciones().getExpectedEndDate())
                            .startDate(UtilGeneral.convertDateToString(ua.getAsignaciones().getExpectedStartDate(),
                                    Constantes.FORMAT_DATE_ONE))
                            .endDate(UtilGeneral.convertDateToString(ua.getAsignaciones().getExpectedEndDate(),
                                    Constantes.FORMAT_DATE_ONE))
                            .status(getNameParameterStatus(listParameter, ua.getAsignaciones().getStatusId()))
                            .build());
        }

        Comparator<ReporteAsignacionDTO> comparator = enumToComparator(reporteAsignacionDTO.getField());

        return reporteAsignacionDTO.getSort().equals(OrdenamientoEnum.ASC) ? list.stream().sorted(comparator).toList()
                : list.stream().sorted(comparator.reversed()).toList();
    }

    /**
     * Devuelve el comparador por el que se desea ordenar la lista de asignaciones
     * 
     * @param filterEnum AsignacionesReporteEnum
     * @return Comparator<ReporteAsignacionDTO>
     */
    private Comparator<ReporteAsignacionDTO> enumToComparator(AsignacionesReporteEnum filterEnum) {
        Comparator<ReporteAsignacionDTO> comparator;
        switch (filterEnum) {
            case RESPONSABLE:
                comparator = Comparator.comparing(ReporteAsignacionDTO::getResponsible);
                break;
            case TAREA:
                comparator = Comparator.comparing(ReporteAsignacionDTO::getTask);
                break;
            case FECHA_INICIO:
                comparator = Comparator.comparing(ReporteAsignacionDTO::getStartDateD);
                break;
            case FECHA_FIN:
                comparator = Comparator.comparing(ReporteAsignacionDTO::getEndDateD);
                break;
            case ESTADO:
                comparator = Comparator.comparing(ReporteAsignacionDTO::getStatus);
                break;
            default:
                comparator = Comparator.comparing(ReporteAsignacionDTO::getResponsible);
                break;
        }
        return comparator;
    }

    /**
     * Obtiene el nombre del parámetro del estado de la asignación
     * 
     * @param listParameter List<Parametros>
     * @param id            Long
     * @return String
     */
    private String getNameParameterStatus(List<Parametros> listParameter, Long id) {
        Parametros param = listParameter.stream().filter(u -> Objects.equals(u.getId(), id)).findFirst().orElse(null);
        return Objects.nonNull(param) ? param.getName() : null;
    }
}
