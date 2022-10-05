package com.retos.bombapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.retos.bombapp.models.DocumentoDTO;
import com.retos.bombapp.repositories.DocumentoRepository;
import com.retos.bombapp.services.DocumentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = Constantes.BEARER_AUTHENTICATION)
@RestController
@RequestMapping("/documentos")
public class DocumentoController {
    @Autowired
    private DocumentoService documentoService;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Operation(summary = "Obtiene los documentos de un proyecto", description = "Obtiene los documentos de un proyecto")
    @GetMapping("/proyecto/{id}")
    public ResponseEntity<Object> getAllByProject(@PathVariable("id") Long id) {
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.OK.getCode(),
                CodigoRespuestaApi.OK.getDescription(),
                CodigoRespuestaApi.OK.getHttpStatus(),
                documentoService.getAllDocumentsByProject(id));
    }

    @Operation(summary = "Inserta un documento", description = "Inserta un documento")
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody DocumentoDTO documentoDTO) {
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.INSERT_OK.getCode(),
                CodigoRespuestaApi.INSERT_OK.getDescription(),
                CodigoRespuestaApi.INSERT_OK.getHttpStatus(),
                documentoService.insertDocumet(documentoDTO));
    }

    @Operation(summary = "Actualiza un documento", description = "Actualiza un documento")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable("id") Long id,
            @RequestBody DocumentoDTO documentoDTO) {
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.UPDATE_OK.getCode(),
                CodigoRespuestaApi.UPDATE_OK.getDescription(),
                CodigoRespuestaApi.UPDATE_OK.getHttpStatus(),
                documentoService.updateDocumet(id, documentoDTO));
    }

    @Operation(summary = "Elimina un documento", description = "Elimina un documento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        documentoRepository.deleteById(id);
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.DELETE_OK.getCode(),
                CodigoRespuestaApi.DELETE_OK.getDescription(),
                CodigoRespuestaApi.DELETE_OK.getHttpStatus());
    }
}
