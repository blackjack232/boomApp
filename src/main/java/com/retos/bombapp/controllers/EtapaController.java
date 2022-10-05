package com.retos.bombapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.handlers.ResponseHandler;
import com.retos.bombapp.models.EtapaDTO;
import com.retos.bombapp.services.EtapaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = Constantes.BEARER_AUTHENTICATION)
@RestController
@RequestMapping("/etapas")
public class EtapaController {
    @Autowired
    private EtapaService etapaService;

    @Operation(summary = "Obtiene las etapas de un proyecto", description = "Obtiene las etapas de un proyecto")
    @GetMapping("/proyecto/{id}")
    public ResponseEntity<Object> getAllByProject(@PathVariable("id") Long id) {
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.OK.getCode(),
                CodigoRespuestaApi.OK.getDescription(),
                CodigoRespuestaApi.OK.getHttpStatus(),
                etapaService.getAllByProject(id));
    }

    @Operation(summary = "Inserta las etapas de un proyecto", description = "Inserta las etapas de un proyecto")
    @PostMapping("/proyecto/lista")
    public ResponseEntity<Object> insertList(@RequestBody EtapaDTO etapaDTO) {
        etapaService.insertStage(etapaDTO);
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.INSERT_OK.getCode(),
                CodigoRespuestaApi.INSERT_OK.getDescription(),
                CodigoRespuestaApi.INSERT_OK.getHttpStatus());
    }
}
