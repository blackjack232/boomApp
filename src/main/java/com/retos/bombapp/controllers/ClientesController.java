package com.retos.bombapp.controllers;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.handlers.ResponseHandler;
import com.retos.bombapp.models.ClienteDTO;
import com.retos.bombapp.repositories.ClienteRepository;
import com.retos.bombapp.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = Constantes.BEARER_AUTHENTICATION)
@RestController
@RequestMapping("/clientes")
public class ClientesController {
        @Autowired
        private ClienteRepository clienteRepository;

        @Autowired
        private ClienteService clienteService;

        @Operation(summary = "Inserta un cliente", description = "Inserta un cliente")
        @PostMapping
        public ResponseEntity<Object> insert(@RequestBody ClienteDTO clienteDTO) {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.INSERT_OK.getCode(),
                                CodigoRespuestaApi.INSERT_OK.getDescription(),
                                CodigoRespuestaApi.INSERT_OK.getHttpStatus(),
                                clienteService.insertClient(clienteDTO));
        }

        @Operation(summary = "Obtiene los clientes", description = "Obtiene los clientes")
        @GetMapping
        public ResponseEntity<Object> getAll() {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.OK.getCode(),
                                CodigoRespuestaApi.OK.getDescription(),
                                CodigoRespuestaApi.OK.getHttpStatus(),
                                clienteRepository.findAll());
        }

        @Operation(summary = "Obtiene un cliente", description = "Obtiene un cliente por {id}")
        @GetMapping("/{id}")
        public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.OK.getCode(),
                                CodigoRespuestaApi.OK.getDescription(),
                                CodigoRespuestaApi.OK.getHttpStatus(),
                                clienteRepository.findById(id).orElse(null));
        }

        @Operation(summary = "Elimina un cliente", description = "Elimina un cliente por {id}")
        @DeleteMapping("/{id}")
        public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
                clienteRepository.deleteById(id);
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.DELETE_OK.getCode(),
                                CodigoRespuestaApi.DELETE_OK.getDescription(),
                                CodigoRespuestaApi.DELETE_OK.getHttpStatus());
        }

        @Operation(summary = "Actualiza un cliente", description = "Actualiza un cliente")
        @PutMapping("/{id}")
        public ResponseEntity<Object> update(
                        @PathVariable("id") Long id,
                        @RequestBody ClienteDTO clienteDTO) {
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.UPDATE_OK.getCode(),
                                CodigoRespuestaApi.UPDATE_OK.getDescription(),
                                CodigoRespuestaApi.UPDATE_OK.getHttpStatus(),
                                clienteService.updateClient(id, clienteDTO));
        }

        @Operation(summary = "Cambia el estado de un cliente", description = "Cambia el estado de un cliente")
        @PutMapping("/cambiar-estado/{id}")
        public ResponseEntity<Object> changeStatus(
                        @PathVariable("id") Long id,
                        @RequestBody ClienteDTO clienteDTO) {
                clienteService.changeStatus(id, clienteDTO.isStatus());
                return ResponseHandler.generarResponse(
                                CodigoRespuestaApi.CLIENT_CHANGE_STATUS_OK.getCode(),
                                CodigoRespuestaApi.CLIENT_CHANGE_STATUS_OK.getDescription(),
                                CodigoRespuestaApi.CLIENT_CHANGE_STATUS_OK.getHttpStatus());
        }
}