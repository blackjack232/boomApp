package com.retos.bombapp.services.impl;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.entities.Usuarios;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.models.EmailDTO;
import com.retos.bombapp.services.EmailService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * Servicio para el envío del email
 */
@Service
@Slf4j
@Getter
@Setter
public class EmailServiceImpl implements EmailService {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendMail(EmailDTO emailDTO) {
        log.info("Enviando email {}", new Date());
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(getFrom());
            helper.setTo(emailDTO.getAddressee());
            helper.setSubject(emailDTO.getSubject());
            helper.setText(emailDTO.getHtml(), true);
            mailSender.send(message);
            log.info("Email enviado {}", new Date());
        } catch (MessagingException e) {
            log.error("Error al enviar el email {}", new Date());
            throw new BombappException(CodigoRespuestaApi.ERROR_SEND_EMAIL);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTemplateHTML(Usuarios usuarios, String password) {
        Context context = new Context();
        context.setVariable("data", usuarios);
        context.setVariable("pass", password);
        return templateEngine.process("email", context);
    }
}
