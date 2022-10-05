package com.retos.bombapp.controllers;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.handlers.ResponseHandler;
import com.retos.bombapp.models.FuncionalidadPermisoDTO;
import com.retos.bombapp.services.PermisoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = Constantes.BEARER_AUTHENTICATION)
@RestController
@RequestMapping("/permisos")
public class PermisoController {
        @Autowired
        private PermisoService permisoService;

        @Operation(summary = "Obtiene los permisos por rol", description = "Obtiene los permisos por el {id} del rol")
        @GetMapping("/rol/{rol}")
        public ResponseEntity<Object> getAllByRol(@PathVariable("rol") Long rol) {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.OK.getCode(),
                                CodigoRespuestaApi.OK.getDescription(),
                                CodigoRespuestaApi.OK.getHttpStatus(),
                                permisoService.getAllByRol(rol));
        }

        @Operation(summary = "Actualiza los permisos por rol", description = "Actualiza los permisos por el {id} del rol")
        @PutMapping
        public ResponseEntity<Object> updateAll(@RequestBody FuncionalidadPermisoDTO funcionalidadPermisoDTO) {
                permisoService.updateByRolPermission(funcionalidadPermisoDTO);
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.UPDATE_OK.getCode(),
                                CodigoRespuestaApi.UPDATE_OK.getDescription(),
                                CodigoRespuestaApi.UPDATE_OK.getHttpStatus());
        }
}
