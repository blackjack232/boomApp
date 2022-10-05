package com.retos.bombapp.controllers;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.handlers.ResponseHandler;
import com.retos.bombapp.models.RolDTO;
import com.retos.bombapp.repositories.RolRepository;
import com.retos.bombapp.services.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST del servicio ROL
 */
@SecurityRequirement(name = Constantes.BEARER_AUTHENTICATION)
@RestController
@RequestMapping("/roles")
public class RolController {
        @Autowired
        private RolRepository rolRepository;

        @Autowired
        private RolService rolService;

        @Operation(summary = "Inserta un rol", description = "Inserta un rol")
        @PostMapping
        ResponseEntity<Object> insert(@RequestBody RolDTO rolDTO) {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.INSERT_OK.getCode(),
                                CodigoRespuestaApi.INSERT_OK.getDescription(),
                                CodigoRespuestaApi.INSERT_OK.getHttpStatus(),
                                rolService.insertRol(rolDTO));
        }

        @Operation(summary = "Obtiene todos los roles", description = "Obtiene todos los roles")
        @GetMapping
        ResponseEntity<Object> getAll() {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.OK.getCode(),
                                CodigoRespuestaApi.OK.getDescription(),
                                CodigoRespuestaApi.OK.getHttpStatus(),
                                rolRepository.findAll());
        }

        @Operation(summary = "Obtiene todos los roles activos", description = "Obtiene todos los roles activos")
        @GetMapping("/visibilidad")
        ResponseEntity<Object> getAllByVisibility() {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.OK.getCode(),
                                CodigoRespuestaApi.OK.getDescription(),
                                CodigoRespuestaApi.OK.getHttpStatus(),
                                rolRepository.findAllByVisibility(true));
        }

        @Operation(summary = "Actualiza un rol", description = "Actualiza un rol")
        @PutMapping("/{id}")
        ResponseEntity<Object> update(
                        @PathVariable("id") Long id,
                        @RequestBody RolDTO rolDTO) {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.UPDATE_OK.getCode(),
                                CodigoRespuestaApi.UPDATE_OK.getDescription(),
                                CodigoRespuestaApi.UPDATE_OK.getHttpStatus(),
                                rolService.updateRol(id, rolDTO));
        }

        @Operation(summary = "Elimina un rol", description = "Elimina un rol por {id}")
        @DeleteMapping("/{id}")
        ResponseEntity<Object> delete(@PathVariable("id") Long id) {
                rolRepository.deleteById(id);
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.DELETE_OK.getCode(),
                                CodigoRespuestaApi.DELETE_OK.getDescription(),
                                CodigoRespuestaApi.DELETE_OK.getHttpStatus());
        }
}
