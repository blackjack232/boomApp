package com.retos.bombapp.repositories;

import com.retos.bombapp.entities.Funcionalidades;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionalidadRepository extends JpaRepository<Funcionalidades, Long> {
}
