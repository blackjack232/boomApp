package com.retos.bombapp.repositories;

import com.retos.bombapp.entities.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para {@link Clientes}
 */
public interface ClienteRepository extends JpaRepository<Clientes, Long> {
}
