package com.retos.bombapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.handlers.ResponseHandler;
import com.retos.bombapp.models.ReporteAsignacionDTO;
import com.retos.bombapp.services.ReporteAsignacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = Constantes.BEARER_AUTHENTICATION)
@RestController
@RequestMapping("/reportes")
public class ReporteController {
    @Autowired
    private ReporteAsignacionService reporteAsignacionService;

    @Operation(summary = "Obtiene las asignaciones por un filtro", description = "Obtiene las asignaciones por un filtro")
    @PostMapping("/asignaciones")
    public ResponseEntity<Object> filterReportAssignment(@RequestBody ReporteAsignacionDTO reporteAsignacionDTO) {
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.OK.getCode(),
                CodigoRespuestaApi.OK.getDescription(),
                CodigoRespuestaApi.OK.getHttpStatus(),
                reporteAsignacionService.filterAllReportByProject(reporteAsignacionDTO));
    }
}
