package com.retos.bombapp.controllers;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.handlers.ResponseHandler;
import com.retos.bombapp.models.CambiarContrasenaDTO;
import com.retos.bombapp.models.CambiarEstadoDTO;
import com.retos.bombapp.models.RecuperarContrasenaDTO;
import com.retos.bombapp.models.UsuarioDTO;
import com.retos.bombapp.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
        @Autowired
        private UsuarioService usuarioService;

        @SecurityRequirement(name = Constantes.BEARER_AUTHENTICATION)
        @Operation(summary = "Inserta un usuario", description = "Inserta un usuario")
        @PostMapping
        public ResponseEntity<Object> insert(@RequestBody UsuarioDTO usuarioDTO) {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.OK.getCode(),
                                CodigoRespuestaApi.OK.getDescription(),
                                CodigoRespuestaApi.OK.getHttpStatus(),
                                usuarioService.insertUser(usuarioDTO));
        }

        @SecurityRequirement(name = Constantes.BEARER_AUTHENTICATION)
        @Operation(summary = "Obtiene todos los usuarios", description = "Consulta todos los usuarios")
        @GetMapping
        public ResponseEntity<Object> getAll() {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.OK.getCode(),
                                CodigoRespuestaApi.OK.getDescription(),
                                CodigoRespuestaApi.OK.getHttpStatus(),
                                usuarioService.getAllUsuariosYCargo());
        }

        @SecurityRequirement(name = Constantes.BEARER_AUTHENTICATION)
        @Operation(summary = "Obtiene el usuario por id", description = "Consulta el usuario por el {id}")
        @GetMapping("/{id}")
        public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.OK.getCode(),
                                CodigoRespuestaApi.OK.getDescription(),
                                CodigoRespuestaApi.OK.getHttpStatus(),
                                usuarioService.getByIdUsuariosYCargo(id));
        }

        @SecurityRequirement(name = Constantes.BEARER_AUTHENTICATION)
        @Operation(summary = "Elimina un usuario", description = "Elimina un usuario por el {id}")
        @DeleteMapping("/{id}")
        public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
                usuarioService.deleteUser(id);
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.DELETE_OK.getCode(),
                                CodigoRespuestaApi.DELETE_OK.getDescription(),
                                CodigoRespuestaApi.DELETE_OK.getHttpStatus());
        }

        @SecurityRequirement(name = Constantes.BEARER_AUTHENTICATION)
        @Operation(summary = "Actualiza un usuario", description = "Actualiza un usuario por el {id}")
        @PutMapping("/{id}")
        public ResponseEntity<Object> update(
                        @PathVariable("id") Long id,
                        @RequestBody UsuarioDTO usuarioDTO) {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.UPDATE_OK.getCode(),
                                CodigoRespuestaApi.UPDATE_OK.getDescription(),
                                CodigoRespuestaApi.UPDATE_OK.getHttpStatus(),
                                usuarioService.updateUser(id, usuarioDTO));
        }

        @Operation(summary = "Recupera la contraseña del usuario", description = "Recupera la contraseña por el {email} del usuario")
        @PutMapping("/recuperar-contrasena")
        public ResponseEntity<Object> recoverPassword(@RequestBody RecuperarContrasenaDTO recuperarContrasenaDTO) {
                usuarioService.recoverPassword(recuperarContrasenaDTO.getEmail());
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.SEND_EMAIL_PASS_OK.getCode(),
                                CodigoRespuestaApi.SEND_EMAIL_PASS_OK.getDescription(),
                                CodigoRespuestaApi.SEND_EMAIL_PASS_OK.getHttpStatus());
        }

        @SecurityRequirement(name = Constantes.BEARER_AUTHENTICATION)
        @Operation(summary = "Cambia la contraseña de un usuario", description = "Cambia la contraseña del usuario por el {id}")
        @PutMapping("/cambiar-contrasena/{id}")
        public ResponseEntity<Object> changePassword(
                        @RequestBody CambiarContrasenaDTO cambiarContrasenaDTO,
                        @PathVariable("id") Long id) {
                usuarioService.changePassword(id, cambiarContrasenaDTO.getPassword());
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.CHANGE_PASS_OK.getCode(),
                                CodigoRespuestaApi.CHANGE_PASS_OK.getDescription(),
                                CodigoRespuestaApi.CHANGE_PASS_OK.getHttpStatus());
        }

        @SecurityRequirement(name = Constantes.BEARER_AUTHENTICATION)
        @Operation(summary = "Cambia el estado del usuario", description = "Cambia el estado del usuario por el {id}")
        @PutMapping("/cambiar-estado/{id}")
        public ResponseEntity<Object> changeStatus(
                        @RequestBody CambiarEstadoDTO cambiarEstadoDTO,
                        @PathVariable("id") Long id) {
                usuarioService.changeStatus(id, cambiarEstadoDTO.getStatus());
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.CHANGE_STATUS_USER_OK.getCode(),
                                CodigoRespuestaApi.CHANGE_STATUS_USER_OK.getDescription(),
                                CodigoRespuestaApi.CHANGE_STATUS_USER_OK.getHttpStatus());
        }
}
