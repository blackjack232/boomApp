package com.retos.bombapp.services;

import java.util.List;

import com.retos.bombapp.models.ReporteAsignacionDTO;

public interface ReporteAsignacionService {
    /**
     * Filtra las asignaciones por una columna de la base de datos para ordenarlas
     * @param reporteAsignacionDTO ReporteAsignacionDTO
     * @return List<ReporteAsignacionDTO>
     */
    List<ReporteAsignacionDTO> filterAllReportByProject(ReporteAsignacionDTO reporteAsignacionDTO);
}
