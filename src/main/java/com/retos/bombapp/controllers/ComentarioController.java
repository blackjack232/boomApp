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
import com.retos.bombapp.models.ComentarioDTO;
import com.retos.bombapp.services.ComentarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = Constantes.BEARER_AUTHENTICATION)
@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;

    @Operation(summary = "Obtiene los comentarios por elemento", description = "Obtiene los comentarios por elemento")
    @GetMapping("/elemento/{id}")
    public ResponseEntity<Object> getAllByElement(@PathVariable("id") Long id) {
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.OK.getCode(),
                CodigoRespuestaApi.OK.getDescription(),
                CodigoRespuestaApi.OK.getHttpStatus(),
                comentarioService.getAllCommentsByElement(id));
    }

    @Operation(summary = "Inserta un comentario", description = "Inserta un comentario")
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody ComentarioDTO comentarioDTO) {
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.INSERT_OK.getCode(),
                CodigoRespuestaApi.INSERT_OK.getDescription(),
                CodigoRespuestaApi.INSERT_OK.getHttpStatus(),
                comentarioService.insertComment(comentarioDTO));
    }

    @Operation(summary = "Obtiene un comentario por id", description = "Obtiene un comentario por id")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.OK.getCode(),
                CodigoRespuestaApi.OK.getDescription(),
                CodigoRespuestaApi.OK.getHttpStatus(),
                comentarioService.getById(id));
    }

    @Operation(summary = "Actualiza un comentario", description = "Actualiza un comentario")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable("id") Long id,
            @RequestBody ComentarioDTO comentarioDTO) {
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.UPDATE_OK.getCode(),
                CodigoRespuestaApi.UPDATE_OK.getDescription(),
                CodigoRespuestaApi.UPDATE_OK.getHttpStatus(),
                comentarioService.updateComment(id, comentarioDTO));
    }

    @Operation(summary = "Elimina un comentario", description = "Elimina un comentario")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        comentarioService.deleteComment(id);
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.DELETE_OK.getCode(),
                CodigoRespuestaApi.DELETE_OK.getDescription(),
                CodigoRespuestaApi.DELETE_OK.getHttpStatus());
    }

    @Operation(summary = "Actualiza el estado de revisión de un comentario", description = "Actualiza el estado de revisión de un comentario")
    @PutMapping("/cambiar-estado/{id}")
    public ResponseEntity<Object> changeStatusReviewed(
            @PathVariable("id") Long id,
            @RequestBody ComentarioDTO comentarioDTO) {
        comentarioService.changeStatusReviewedComment(id, comentarioDTO.isReviewed());
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.CHANGE_STATUS_COMMENT_OK.getCode(),
                CodigoRespuestaApi.CHANGE_STATUS_COMMENT_OK.getDescription(),
                CodigoRespuestaApi.CHANGE_STATUS_COMMENT_OK.getHttpStatus());
    }
}
