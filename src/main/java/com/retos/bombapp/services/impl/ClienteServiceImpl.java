package com.retos.bombapp.services.impl;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.entities.Clientes;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.models.ClienteDTO;
import com.retos.bombapp.repositories.ClienteRepository;
import com.retos.bombapp.services.ClienteService;
import com.retos.bombapp.services.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Servicio para el cliente
 */
@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UtilService utilService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Clientes insertClient(ClienteDTO clienteDTO) {
        clienteDTO.setId(null);
        Clientes clientes = (Clientes) utilService.mapDTO(clienteDTO, new Clientes());
        clientes.setStatus(true);

        Clientes clientesInsert;
        try {
            clientesInsert = clienteRepository.save(clientes);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_INSERT_CLIENT);
        }

        return clientesInsert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Clientes updateClient(Long id, ClienteDTO clienteDTO) {
        Clientes clientes = clienteRepository.findById(id).orElse(null);

        if (Objects.isNull(clientes)) {
            throw new BombappException(CodigoRespuestaApi.CLIENT_NOT_FOUND);
        }

        Clientes clientesMap = (Clientes) utilService.mapDTO(clienteDTO, new Clientes());
        clientesMap.setId(id);

        Clientes clientesUpdate;
        try {
            clientesUpdate = clienteRepository.save(clientesMap);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_UPDATE_CLIENT);
        }

        return clientesUpdate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeStatus(Long id, boolean status) {
        Clientes clientes = clienteRepository.findById(id).orElse(null);

        if (Objects.isNull(clientes)) {
            throw new BombappException(CodigoRespuestaApi.CLIENT_NOT_FOUND);
        }

        if (status == clientes.isStatus()) return;

        clientes.setStatus(status);
        try {
            clienteRepository.save(clientes);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.CLIENT_CHANGE_STATUS_ERROR);
        }
    }
}
