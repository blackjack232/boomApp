package com.retos.bombapp.controllers;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.handlers.ResponseHandler;
import com.retos.bombapp.services.ConfiguracionInicialService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/configuracion-inicial")
public class ConfiguracionInicialController {
    @Autowired
    private ConfiguracionInicialService configuracionInicialService;

    @Hidden
    @Operation(summary = "Realiza la configuración inicial", description = "Realiza la configuración inicial de la base de datos")
    @PostMapping
    public ResponseEntity<Object> config() {
        configuracionInicialService.config();
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.CONFIG_INITIAL_OK.getCode(),
                CodigoRespuestaApi.CONFIG_INITIAL_OK.getDescription(),
                CodigoRespuestaApi.CONFIG_INITIAL_OK.getHttpStatus());
    }
}
