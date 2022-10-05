package com.retos.bombapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.handlers.ResponseHandler;
import com.retos.bombapp.models.AsignacionDTO;
import com.retos.bombapp.services.AsignacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = Constantes.BEARER_AUTHENTICATION)
@RestController
@RequestMapping("/asignaciones")
public class AsignacionController {
    @Autowired
    private AsignacionService asignacionService;

    @Operation(summary = "Obtiene las asignaciones por proyecto", description = "Obtiene las asignaciones por proyecto")
    @GetMapping("/proyecto/{id}")
    public ResponseEntity<Object> getAllByProject(
            @PathVariable("id") Long id,
            @RequestHeader(name = Constantes.AUTHORIZATION) String token) {
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.OK.getCode(),
                CodigoRespuestaApi.OK.getDescription(),
                CodigoRespuestaApi.OK.getHttpStatus(),
                asignacionService.getAllByProject(id, token));
    }

    @Operation(summary = "Obtiene una asignación por id", description = "Obtiene una asignación por id")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.OK.getCode(),
                CodigoRespuestaApi.OK.getDescription(),
                CodigoRespuestaApi.OK.getHttpStatus(),
                asignacionService.getById(id));
    }

    @Operation(summary = "Inserta una asignación", description = "Inserta una asignación")
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody AsignacionDTO asignacionDTO) {
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.INSERT_OK.getCode(),
                CodigoRespuestaApi.INSERT_OK.getDescription(),
                CodigoRespuestaApi.INSERT_OK.getHttpStatus(),
                asignacionService.insertAssignment(asignacionDTO));
    }

    @Operation(summary = "Actualiza una asignación por id", description = "Actualiza una asignación por id")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable("id") Long id,
            @RequestBody AsignacionDTO asignacionDTO) {
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.UPDATE_OK.getCode(),
                CodigoRespuestaApi.UPDATE_OK.getDescription(),
                CodigoRespuestaApi.UPDATE_OK.getHttpStatus(),
                asignacionService.updateAssignment(id, asignacionDTO));
    }

    @Operation(summary = "Elimina una asignación por id", description = "Elimina una asignación por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        asignacionService.deleteAssignment(id);
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.DELETE_OK.getCode(),
                CodigoRespuestaApi.DELETE_OK.getDescription(),
                CodigoRespuestaApi.DELETE_OK.getHttpStatus());
    }
}
