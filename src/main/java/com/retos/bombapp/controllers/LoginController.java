package com.retos.bombapp.controllers;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.handlers.ResponseHandler;
import com.retos.bombapp.models.LoginDTO;
import com.retos.bombapp.services.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST del servicio LOGIN
 */
@RestController
@RequestMapping("/acceso")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Operation(summary = "Permite el acceso al aplicativo", description = "Permite el acceso al aplicativo por {email} y {contraseña}")
    @PostMapping
    ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO) {
        return ResponseHandler.generarResponse(
                CodigoRespuestaApi.OK.getCode(),
                CodigoRespuestaApi.OK.getDescription(),
                CodigoRespuestaApi.OK.getHttpStatus(),
                loginService.authenticate(loginDTO));
    }
}
