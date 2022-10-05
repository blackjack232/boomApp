package com.retos.bombapp.services;

import java.util.Map;

import com.retos.bombapp.models.PdfDTO;
import com.retos.bombapp.models.ReporteAsignacionDTO;

public interface GenerarPDFService {
    /**
     * Genera el PDF de la plantilla enviada
     * 
     * @param templateName String
     * @param data         Map<String, Object>
     * @return String
     */
    String generatePdf(String templateName, Map<String, Object> data);

    /**
     * Genera el PDF del reporte de asignaciones
     * @param reporteAsignacionDTO ReporteAsignacionDTO
     * @return PdfDTO
     */
    PdfDTO reporteAsignacionesPdf(ReporteAsignacionDTO reporteAsignacionDTO);
}
