package com.retos.bombapp.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.entities.Documentos;
import com.retos.bombapp.entities.Proyectos;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.models.DocumentoDTO;
import com.retos.bombapp.repositories.DocumentoRepository;
import com.retos.bombapp.services.DocumentoService;
import com.retos.bombapp.services.UtilService;

/**
 * Servicio para los documentos del proyecto
 */
@Service
public class DocumentoServiceImpl implements DocumentoService {
    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private UtilService utilService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DocumentoDTO> getAllDocumentsByProject(Long idProject) {
        List<Documentos> listDocuments = documentoRepository.findAllByProyectos(Proyectos.builder().id(idProject).build());

        List<DocumentoDTO> listDocumentDTO = new ArrayList<>();
        for (Documentos d: listDocuments) {
            DocumentoDTO documentoDTO = (DocumentoDTO) utilService.mapDTO(d, new DocumentoDTO());
            listDocumentDTO.add(documentoDTO);
        }

        return listDocumentDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Documentos insertDocumet(DocumentoDTO documentoDTO) {
        documentoDTO.setId(null);
        Documentos documentos = (Documentos) utilService.mapDTO(documentoDTO, new Documentos());
        documentos.setProyectos(Proyectos.builder().id(documentoDTO.getProjectId()).build());

        Documentos documentosInsert;
        try {
            documentosInsert = documentoRepository.save(documentos);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_INSERT_DOCUMENT);
        }

        return documentosInsert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Documentos updateDocumet(Long id, DocumentoDTO documentoDTO) {
        Documentos doc = documentoRepository.findById(id).orElse(null);

        if (Objects.isNull(doc)) {
            throw new BombappException(CodigoRespuestaApi.DOCUMENT_NOT_FOUND);
        }

        Documentos documentos = (Documentos) utilService.mapDTO(documentoDTO, new Documentos());
        documentos.setId(id);
        documentos.setProyectos(Proyectos.builder().id(documentoDTO.getProjectId()).build());

        Documentos documentosUpdate;
        try {
            documentosUpdate = documentoRepository.save(documentos);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_UPDATE_DOCUMENT);
        }

        return documentosUpdate;
    }
    
}
