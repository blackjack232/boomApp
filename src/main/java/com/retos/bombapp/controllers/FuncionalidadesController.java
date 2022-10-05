package com.retos.bombapp.controllers;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.handlers.ResponseHandler;
import com.retos.bombapp.models.FuncionalidadDTO;
import com.retos.bombapp.services.FuncionalidadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = Constantes.BEARER_AUTHENTICATION)
@RestController
@RequestMapping("/funcionalidades")
public class FuncionalidadesController {
    @Autowired
    private FuncionalidadService funcionalidadService;

    @Operation(summary = "Inserta una funcionalidad", description = "Inserta una funcionalidad")
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody FuncionalidadDTO funcionalidadDTO) {
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.INSERT_OK.getCode(),
                CodigoRespuestaApi.INSERT_OK.getDescription(),
                CodigoRespuestaApi.INSERT_OK.getHttpStatus(),
                funcionalidadService.insertFunctionality(funcionalidadDTO));
    }
}
