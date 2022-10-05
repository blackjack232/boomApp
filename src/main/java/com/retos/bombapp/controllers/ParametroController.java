package com.retos.bombapp.controllers;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.handlers.ResponseHandler;
import com.retos.bombapp.models.ParametroDTO;
import com.retos.bombapp.repositories.ParametroRepository;
import com.retos.bombapp.services.ParametroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = Constantes.BEARER_AUTHENTICATION)
@RestController
@RequestMapping("/parametros")
public class ParametroController {
        @Autowired
        private ParametroRepository parametroRepository;

        @Autowired
        private ParametroService parametroService;

        @Operation(summary = "Inserta un parámetro", description = "Inserta un parámetro")
        @PostMapping
        public ResponseEntity<Object> insert(@RequestBody ParametroDTO parametroDTO) {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.INSERT_OK.getCode(),
                                CodigoRespuestaApi.INSERT_OK.getDescription(),
                                CodigoRespuestaApi.INSERT_OK.getHttpStatus(),
                                parametroService.insertParameter(parametroDTO));
        }

        @Operation(summary = "Obtiene los tipos de parámetros", description = "Obtiene los tipos de parámetros")
        @GetMapping
        public ResponseEntity<Object> getAllOnlyTypes() {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.OK.getCode(),
                                CodigoRespuestaApi.OK.getDescription(),
                                CodigoRespuestaApi.OK.getHttpStatus(),
                                parametroService.getAllOnlyTypes());
        }

        @Operation(summary = "Obtiene los parámetros por tipo", description = "Obtiene los parámetros por {id}")
        @PostMapping("/tipo")
        public ResponseEntity<Object> getByType(
                        @RequestBody ParametroDTO parametroDTO) {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.OK.getCode(),
                                CodigoRespuestaApi.OK.getDescription(),
                                CodigoRespuestaApi.OK.getHttpStatus(),
                                parametroService.getAllByType(parametroDTO.getType()));
        }

        @Operation(summary = "Obtiene un parámetro por tipo", description = "Obtiene un parámetro por {tipo}")
        @GetMapping("/{id}")
        public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.OK.getCode(),
                                CodigoRespuestaApi.OK.getDescription(),
                                CodigoRespuestaApi.OK.getHttpStatus(),
                                parametroRepository.findById(id).orElse(null));
        }

        @Operation(summary = "Actualiza un parámetro", description = "Actualiza un parámetro")
        @PutMapping("/{id}")
        public ResponseEntity<Object> update(
                        @PathVariable("id") Long id,
                        @RequestBody ParametroDTO parametroDTO) {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.UPDATE_OK.getCode(),
                                CodigoRespuestaApi.UPDATE_OK.getDescription(),
                                CodigoRespuestaApi.UPDATE_OK.getHttpStatus(),
                                parametroService.updateParameter(id, parametroDTO));
        }

        @Operation(summary = "Elimina un parámetro", description = "Elimina un parámetro por {id}")
        @DeleteMapping("/{id}")
        public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
                parametroRepository.deleteById(id);
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.DELETE_OK.getCode(),
                                CodigoRespuestaApi.DELETE_OK.getDescription(),
                                CodigoRespuestaApi.DELETE_OK.getHttpStatus());
        }

        @Operation(summary = "Actualiza el estado de un parámetro", description = "Actualiza el estado de un parámetro")
        @PutMapping("/cambiar-estado/{id}")
        public ResponseEntity<Object> changeStatus(
                        @PathVariable("id") Long id,
                        @RequestBody ParametroDTO parametroDTO) {
                parametroService.changeStatus(id, parametroDTO.getStatus());
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.PARAMETER_CHANGE_STATUS_OK.getCode(),
                                CodigoRespuestaApi.PARAMETER_CHANGE_STATUS_OK.getDescription(),
                                CodigoRespuestaApi.PARAMETER_CHANGE_STATUS_OK.getHttpStatus());
        }

        @Operation(summary = "Actualiza el orden de un parámetro", description = "Actualiza el orden de un parámetro")
        @PutMapping("/cambiar-orden/{id}")
        public ResponseEntity<Object> changeOrder(
                        @PathVariable("id") Long id,
                        @RequestBody ParametroDTO parametroDTO) {
                parametroService.changeOrder(id, parametroDTO.isAction());
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.PARAMETER_CHANGE_ORDER_OK.getCode(),
                                CodigoRespuestaApi.PARAMETER_CHANGE_ORDER_OK.getDescription(),
                                CodigoRespuestaApi.PARAMETER_CHANGE_ORDER_OK.getHttpStatus());
        }
}
