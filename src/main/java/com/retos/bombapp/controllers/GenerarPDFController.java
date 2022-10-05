package com.retos.bombapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.handlers.ResponseHandler;
import com.retos.bombapp.models.ReporteAsignacionDTO;
import com.retos.bombapp.services.GenerarPDFService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/generar-pdf")
public class GenerarPDFController {
    @Autowired
    private GenerarPDFService generarPDFService;

    @Operation(summary = "Genera el PDF del reporte de asignaciones", description = "Genera el PDF del reporte de asignaciones")
    @PostMapping("/asignaciones-proyecto")
    public ResponseEntity<Object> generatePDF(@RequestBody ReporteAsignacionDTO reporteAsignacionDTO) {
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.OK.getCode(),
                CodigoRespuestaApi.OK.getDescription(),
                CodigoRespuestaApi.OK.getHttpStatus(),
                generarPDFService.reporteAsignacionesPdf(reporteAsignacionDTO));
    }
}
