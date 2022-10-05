package com.retos.bombapp.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.entities.Asignaciones;
import com.retos.bombapp.entities.Elementos;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.models.ElementoDTO;
import com.retos.bombapp.repositories.ElementoRepository;
import com.retos.bombapp.services.ElementoService;
import com.retos.bombapp.services.UtilService;
import com.retos.bombapp.utils.UtilGeneral;

/**
 * Servicio para elementos
 */
@Service
public class ElementoServiceImpl implements ElementoService {

    @Autowired
    private ElementoRepository elementoRepository;

    @Autowired
    private UtilService utilService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ElementoDTO> getAllElementsByAssignment(Long id) {
        List<Elementos> listElements = elementoRepository.findAllByAsignacionesAndStatus(Asignaciones.builder().id(id).build(), Constantes.ACTIVE);

        List<ElementoDTO> listElementsDTO = new ArrayList<>();
        for (Elementos e : listElements) {
            listElementsDTO.add(mapDTO(e));
        }

        return listElementsDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ElementoDTO getElementById(Long id) {
        Elementos elementos = elementoRepository.findById(id).orElse(null);

        if (Objects.isNull(elementos)) {
            throw new BombappException(CodigoRespuestaApi.ELEMENT_NOT_FOUND);
        }

        return mapDTO(elementos);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Elementos insertElement(ElementoDTO elementoDTO) {
        elementoDTO.setId(null);
        Elementos elementos = (Elementos) utilService.mapDTO(elementoDTO, new Elementos());
        elementos.setAsignaciones(Asignaciones.builder().id(elementoDTO.getAssignmentId()).build());
        elementos.setUploadDate(new Date());
        elementos.setStatus(Constantes.ACTIVE);

        Elementos elementosInsert;
        try {
            elementosInsert = elementoRepository.save(elementos);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_INSERT_ELEMENT);
        }

        return elementosInsert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Elementos updateElement(Long id, ElementoDTO elementoDTO) {
        Elementos element = elementoRepository.findById(id).orElse(null);

        if (Objects.isNull(element)) {
            throw new BombappException(CodigoRespuestaApi.ELEMENT_NOT_FOUND);
        }

        Elementos elementos = (Elementos) utilService.mapDTO(elementoDTO, new Elementos());
        elementos.setId(id);
        elementos.setAsignaciones(Asignaciones.builder().id(elementoDTO.getAssignmentId()).build());
        elementos.setUploadDate(new Date());

        Elementos elementosUpdate;
        try {
            elementosUpdate = elementoRepository.save(elementos);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_UPDATE_ELEMENT);
        }

        return elementosUpdate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeStatusElement(Long id, String status) {
        Elementos elementos = elementoRepository.findById(id).orElse(null);

        if (Objects.isNull(elementos)) {
            throw new BombappException(CodigoRespuestaApi.ELEMENT_NOT_FOUND);
        }

        if (status.equalsIgnoreCase(elementos.getStatus())) {
            return;
        }
        
        elementos.setStatus(status.equalsIgnoreCase(Constantes.ACTIVE) ? Constantes.ACTIVE : Constantes.INACTIVE);

        try {
            elementoRepository.save(elementos);
        } catch (Exception ex) {
            throw new BombappException(CodigoRespuestaApi.NO_CHANGE_STATUS_ELEMENT);
        }
    }

    // ------------------------------- PRIVADOS --------------------------------
    /**
     * Mapea de Elementos a ElementoDTO
     *
     * @param e Elementos
     * @return ElementoDTO
     */
    private ElementoDTO mapDTO(Elementos e) {
        ElementoDTO elementoDTO = (ElementoDTO) utilService.mapDTO(e, new ElementoDTO());
        elementoDTO.setAssignmentId(Objects.nonNull(e.getAsignaciones()) ? e.getAsignaciones().getId() : null);
        elementoDTO.setUploadDate(UtilGeneral.convertDateToString(e.getUploadDate(), Constantes.FORMAT_DATE_ONE));
        return elementoDTO;
    }
}
