package com.retos.bombapp.services;

import java.util.List;

import com.retos.bombapp.models.EtapaDTO;

public interface EtapaService {
    /**
     * Obtiene todas las etapas
     * 
     * @param idProject
     * @return List<EtapaDTO>
     */
    List<EtapaDTO> getAllByProject(Long idProject);

    /**
     * Inserta una etapa
     * 
     * @param etapaDTO
     */
    void insertStage(EtapaDTO etapaDTO);
}
