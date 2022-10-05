package com.retos.bombapp.services.impl;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.exceptions.BombappException;
import com.retos.bombapp.models.TokenDTO;
import com.retos.bombapp.security.JwtTokenUtil;
import com.retos.bombapp.services.UtilService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Objects;

/**
 * Servicio de utilidades
 */
@Service
@Slf4j
public class UtilServiceImpl implements UtilService {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * {@inheritDoc}
     */
    @Override
    public Object fromJsonToDTO(String json, Object object) {
        try {
            return objectMapper.readValue(json, object.getClass());
        } catch (JsonProcessingException e) {
            return object;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object mapDTO(Object obj, Object objMap) {
        return Objects.nonNull(obj) ? modelMapper.map(obj, objMap.getClass()) : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String writeToString(Object obj) {
        String str = null;
        try {
            str = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.info("Error al escribir el objeto a string.");
        }
        return str;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TokenDTO decodePayloadToMapDTO(String token) {
        if (Objects.isNull(token) || token.isEmpty()) {
            throw new BombappException(CodigoRespuestaApi.NOT_FOUND_TOKEN);
        }
        
        token = token.substring(7);

        String payload = jwtTokenUtil.getPayload(token);

        if (Objects.isNull(payload)) {
            throw new BombappException(CodigoRespuestaApi.PAYLOAD_NOT_FOUND_TOKEN);
        }
        
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String decodedPayload = new String(decoder.decode(payload));        

        return (TokenDTO) fromJsonToDTO(decodedPayload, new TokenDTO());
    }
}
