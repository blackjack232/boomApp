package com.retos.bombapp.services;

import java.util.List;

import com.retos.bombapp.entities.Comentarios;
import com.retos.bombapp.models.ComentarioDTO;

public interface ComentarioService {
    /**
     * Inserta un comentario
     * 
     * @param comentarioDTO ComentarioDTO
     * @return Comentarios
     */
    Comentarios insertComment(ComentarioDTO comentarioDTO);

    /**
     * Obtiene todos los comentarios por elemento
     * 
     * @param idElement Long
     * @return List<ComentarioDTO>
     */
    List<ComentarioDTO> getAllCommentsByElement(Long idElement);

    /**
     * Obtiene un comentario por id
     * 
     * @param id Long
     * @return ComentarioDTO
     */
    ComentarioDTO getById(Long id);

    /**
     * Actualiza un comentario
     * 
     * @param id            Long
     * @param comentarioDTO ComentarioDTO
     * @return Comentarios
     */
    Comentarios updateComment(Long id, ComentarioDTO comentarioDTO);

    /**
     * Eliminar un comentario por id
     * 
     * @param id Long
     */
    void deleteComment(Long id);

    /**
     * Cambia el estado de revisión del comentario por id
     * 
     * @param id         Long
     * @param isReviewed boolean
     */
    void changeStatusReviewedComment(Long id, boolean isReviewed);
}
