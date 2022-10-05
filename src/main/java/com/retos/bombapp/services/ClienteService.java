package com.retos.bombapp.services;

import com.retos.bombapp.entities.Clientes;
import com.retos.bombapp.models.ClienteDTO;

public interface ClienteService {
    /**
     * Inserta el cliente en la base de datos
     *
     * @param clienteDTO ClienteDB
     * @return Clientes
     */
    Clientes insertClient(ClienteDTO clienteDTO);

    /**
     * Actualiza el cliente en la base de datos por el id
     *
     * @param id         Long
     * @param clienteDTO ClienteDTO
     * @return Clientes
     */
    Clientes updateClient(Long id, ClienteDTO clienteDTO);

    /**
     * Cambia el estado del cliente
     * @param id Long
     * @param status boolean
     */
    void changeStatus(Long id, boolean status);
}
