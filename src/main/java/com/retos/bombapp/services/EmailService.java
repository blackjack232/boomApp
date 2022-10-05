package com.retos.bombapp.services;

import com.retos.bombapp.entities.Usuarios;
import com.retos.bombapp.models.EmailDTO;

public interface EmailService {
    /**
     * Envía el correo electrónico
     * @param emailDTO EmailDTO
     */
    void sendMail(EmailDTO emailDTO);

    /**
     * Obtiene la plantilla del email
     * @param usuarios Usuarios
     * @param password String
     * @return String
     */
    String getTemplateHTML(Usuarios usuarios, String password);
}
