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
import com.retos.bombapp.models.ProyectoDTO;
import com.retos.bombapp.services.ProyectoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = Constantes.BEARER_AUTHENTICATION)
@RestController
@RequestMapping("/proyectos")
public class ProyectoController {
        @Autowired
        private ProyectoService proyectoService;

        @Operation(summary = "Obtiene los proyectos de un usuario", description = "Obtiene los proyectos de un usuario")
        @GetMapping()
        public ResponseEntity<Object> getAllByRol(@RequestHeader(name = Constantes.AUTHORIZATION) String token) {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.OK.getCode(),
                                CodigoRespuestaApi.OK.getDescription(),
                                CodigoRespuestaApi.OK.getHttpStatus(),
                                proyectoService.getAllProjectsByRol(token));
        }

        @Operation(summary = "Obtiene un proyecto por id", description = "Obtiene un proyecto por id")
        @GetMapping("/{id}")
        public ResponseEntity<Object> getByID(@PathVariable("id") Long id) {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.OK.getCode(),
                                CodigoRespuestaApi.OK.getDescription(),
                                CodigoRespuestaApi.OK.getHttpStatus(),
                                proyectoService.getById(id));
        }

        @Operation(summary = "Inserta un proyecto", description = "Inserta un proyecto")
        @PostMapping
        public ResponseEntity<Object> insert(@RequestBody ProyectoDTO proyectoDTO) {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.INSERT_OK.getCode(),
                                CodigoRespuestaApi.INSERT_OK.getDescription(),
                                CodigoRespuestaApi.INSERT_OK.getHttpStatus(),
                                proyectoService.insertProject(proyectoDTO));
        }

        @Operation(summary = "Actualiza un proyecto", description = "Actualiza un proyecto por id")
        @PutMapping("/{id}")
        public ResponseEntity<Object> update(
                        @PathVariable("id") Long id,
                        @RequestBody ProyectoDTO proyectoDTO) {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.UPDATE_OK.getCode(),
                                CodigoRespuestaApi.UPDATE_OK.getDescription(),
                                CodigoRespuestaApi.UPDATE_OK.getHttpStatus(),
                                proyectoService.updateProject(id, proyectoDTO));
        }

        @Operation(summary = "Elimina un proyecto", description = "Elimina un proyecto por id")
        @DeleteMapping("/{id}")
        public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
                proyectoService.deleteProject(id);
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.DELETE_OK.getCode(),
                                CodigoRespuestaApi.DELETE_OK.getDescription(),
                                CodigoRespuestaApi.DELETE_OK.getHttpStatus());
        }
}
