package com.retos.bombapp.services;

import java.util.List;

import com.retos.bombapp.entities.Documentos;
import com.retos.bombapp.models.DocumentoDTO;

public interface DocumentoService {
    /**
     * Obtiene todos los documentos asociados al proyecto
     * 
     * @param idProject Long
     * @return List<DocumentoDTO>
     */
    List<DocumentoDTO> getAllDocumentsByProject(Long idProject);

    /**
     * Inserta un documento
     * 
     * @param documentoDTO DocumentoDTO
     * @return Documentos
     */
    Documentos insertDocumet(DocumentoDTO documentoDTO);

    /**
     * Actualiza un documento
     * 
     * @param id           Long
     * @param documentoDTO DocumentoDTO
     * @return Documentos
     */
    Documentos updateDocumet(Long id, DocumentoDTO documentoDTO);
}
