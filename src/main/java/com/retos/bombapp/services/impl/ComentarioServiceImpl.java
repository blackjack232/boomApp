package com.retos.bombapp.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.entities.Comentarios;
import com.retos.bombapp.entities.Elementos;
import com.retos.bombapp.entities.Usuarios;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.models.ComentarioDTO;
import com.retos.bombapp.repositories.ComentarioRepository;
import com.retos.bombapp.services.ComentarioService;
import com.retos.bombapp.services.UtilService;
import com.retos.bombapp.utils.UtilGeneral;

@Service
public class ComentarioServiceImpl implements ComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UtilService utilService;

    @Override
    public Comentarios insertComment(ComentarioDTO comentarioDTO) {
        comentarioDTO.setId(null);

        Comentarios comentarios = (Comentarios) utilService.mapDTO(comentarioDTO, new Comentarios());
        comentarios.setCreationDate(new Date());

        if (Objects.nonNull(comentarioDTO.getElementId()))
            comentarios.setElementos(Elementos.builder().id(comentarioDTO.getElementId()).build());

        if (Objects.nonNull(comentarioDTO.getUserId()))
            comentarios.setUsuarios(Usuarios.builder().id(comentarioDTO.getUserId()).build());

        Comentarios comentariosInsert;
        try {
            comentariosInsert = comentarioRepository.save(comentarios);
        } catch (Exception e) {
            throw new BombappException(CodigoRespuestaApi.NO_INSERT_COMMENT);
        }

        return comentariosInsert;
    }

    @Override
    public List<ComentarioDTO> getAllCommentsByElement(Long idElement) {
        List<Comentarios> listComments = comentarioRepository
                .findAllByElementos(Elementos.builder().id(idElement).build());

        List<ComentarioDTO> lDtos = new ArrayList<>();
        for (Comentarios c : listComments) {
            ComentarioDTO comentarioDTO = (ComentarioDTO) utilService.mapDTO(c, new ComentarioDTO());
            comentarioDTO.setElementId(idElement);
            comentarioDTO.setCreationDate(UtilGeneral.convertDateToString(c.getCreationDate(), Constantes.FORMAT_DATE_TWO));
            lDtos.add(comentarioDTO);
        }

        return lDtos;
    }

    @Override
    public ComentarioDTO getById(Long id) {
        Comentarios comentarios = comentarioRepository.findById(id).orElse(null);

        if (Objects.isNull(comentarios)) {
            throw new BombappException(CodigoRespuestaApi.COMMENT_NOT_FOUND);
        }

        ComentarioDTO comentarioDTO = (ComentarioDTO) utilService.mapDTO(comentarios, new ComentarioDTO());
        comentarioDTO.setElementId(Objects.nonNull(comentarios.getElementos()) ? comentarios.getElementos().getId() : null);
        comentarioDTO.setCreationDate(UtilGeneral.convertDateToString(comentarios.getCreationDate(), Constantes.FORMAT_DATE_TWO));
        return comentarioDTO;
    }

    @Override
    public Comentarios updateComment(Long id, ComentarioDTO comentarioDTO) {
        Comentarios c = comentarioRepository.findById(id).orElse(null);

        if (Objects.isNull(c)) {
            throw new BombappException(CodigoRespuestaApi.COMMENT_NOT_FOUND);
        }

        Comentarios comentarios = (Comentarios) utilService.mapDTO(comentarioDTO, new Comentarios());
        comentarios.setId(id);
        comentarios.setCreationDate(new Date());

        if (Objects.nonNull(comentarioDTO.getElementId()))
            comentarios.setElementos(Elementos.builder().id(comentarioDTO.getElementId()).build());

        if (Objects.nonNull(comentarioDTO.getUserId()))
            comentarios.setUsuarios(Usuarios.builder().id(comentarioDTO.getUserId()).build());

        Comentarios comentariosUpdate;
        try {
            comentariosUpdate = comentarioRepository.save(comentarios);
            comentariosUpdate.setElementos(null);
        } catch (Exception e) {
            throw new BombappException(CodigoRespuestaApi.NO_UPDATE_COMMENT);
        }

        return comentariosUpdate;
    }

    @Override
    public void deleteComment(Long id) {
        comentarioRepository.deleteById(id);
    }

    @Override
    public void changeStatusReviewedComment(Long id, boolean isReviewed) {
        Comentarios comentarios = comentarioRepository.findById(id).orElse(null);

        if (Objects.isNull(comentarios)) {
            throw new BombappException(CodigoRespuestaApi.COMMENT_NOT_FOUND);
        }

        comentarios.setReviewed(isReviewed);
        comentarios.setCreationDate(new Date());

        try {
            comentarioRepository.save(comentarios);
        } catch (Exception e) {
            throw new BombappException(CodigoRespuestaApi.NO_CHANGE_STATUS_COMMENT);
        }
    }

}
