package com.retos.bombapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.handlers.ResponseHandler;
import com.retos.bombapp.models.ElementoDTO;
import com.retos.bombapp.services.ElementoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = Constantes.BEARER_AUTHENTICATION)
@RestController
@RequestMapping("/elementos")
public class ElementoController {

    @Autowired
    private ElementoService elementoService;

    @Operation(summary = "Obtiene todos los elementos por asignación", description = "Obtiene todos los elementos por asignación")
    @GetMapping("/asignaciones/{id}")
    public ResponseEntity<Object> getAllByAssignment(@PathVariable("id") Long id) {
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.OK.getCode(),
                CodigoRespuestaApi.OK.getDescription(),
                CodigoRespuestaApi.OK.getHttpStatus(),
                elementoService.getAllElementsByAssignment(id));
    }

    @Operation(summary = "Obtiene un elemento por id", description = "Obtiene un elemento por id")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.OK.getCode(),
                CodigoRespuestaApi.OK.getDescription(),
                CodigoRespuestaApi.OK.getHttpStatus(),
                elementoService.getElementById(id));
    }

    @Operation(summary = "Inserta un elemento", description = "Inserta un elemento")
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody ElementoDTO elementoDTO) {
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.INSERT_OK.getCode(),
                CodigoRespuestaApi.INSERT_OK.getDescription(),
                CodigoRespuestaApi.INSERT_OK.getHttpStatus(),
                elementoService.insertElement(elementoDTO));
    }

    @Operation(summary = "Actualiza un elemento", description = "Actualiza un elemento")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable("id") Long id,
            @RequestBody ElementoDTO elementoDTO) {
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.UPDATE_OK.getCode(),
                CodigoRespuestaApi.UPDATE_OK.getDescription(),
                CodigoRespuestaApi.UPDATE_OK.getHttpStatus(),
                elementoService.updateElement(id, elementoDTO));
    }

    @Operation(summary = "Cambia el estado de un elemento", description = "Cambia el estado de un elemento")
    @PutMapping("/cambiar-estado/{id}")
    public ResponseEntity<Object> changeStatus(
            @PathVariable("id") Long id,
            @RequestBody ElementoDTO elementoDTO) {
        elementoService.changeStatusElement(id, elementoDTO.getStatus());
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.CHANGE_STATUS_ELEMENT_OK.getCode(),
                CodigoRespuestaApi.CHANGE_STATUS_ELEMENT_OK.getDescription(),
                CodigoRespuestaApi.CHANGE_STATUS_ELEMENT_OK.getHttpStatus());
    }
}
