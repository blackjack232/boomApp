package com.retos.bombapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.retos.bombapp.entities.Usuarios;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Objects;

/**
 * Utilidades para JWT token
 */
@Service
@Getter
@Setter
public class JwtTokenUtil {
    private Algorithm algorithmHS;
    private JWTVerifier verifier;
    @Value("${jwt.api.issuer}")
    private String loginApiIssuer;
    @Value("${jwt.api.jwtExpiration}")
    private int jwtExpiration;
    @Value("${jwt.api.secretKey}")
    private String secretKey;

    /**
     * Método que genera el token de acceso
     *
     * @param usuarios Usuarios
     * @return String
     */
    public String generateAccessToken(Usuarios usuarios) {
        Calendar current = Calendar.getInstance();
        Calendar expiredAt = Calendar.getInstance();
        expiredAt.add(Calendar.MINUTE, getJwtExpiration());
        return JWT.create()
                .withIssuer(getLoginApiIssuer())
                .withSubject(usuarios.getEmail())
                .withIssuedAt(current.getTime())
                .withExpiresAt(expiredAt.getTime())
                .withClaim("userId", usuarios.getId())
                .withClaim("userNames", usuarios.getNames())
                .withClaim("userLastnames", usuarios.getLastnames())
                .withClaim("userType", usuarios.getType())
                .withClaim("rolCode", usuarios.getRoles().getCode())
                .withClaim("rolName", usuarios.getRoles().getName())
                .sign(getAlgorithmHS());
    }

    /**
     * Método que valida el token
     *
     * @param token String
     * @return boolean
     */
    public boolean validateToken(final String token) {
        return getDecodedJWT(token) != null;
    }

    /**
     * Método que obtine el subject del token
     *
     * @param token String
     * @return String
     */
    public String getSubject(final String token) {
        DecodedJWT decoded = getDecodedJWT(token);
        return !Objects.isNull(decoded) ? decoded.getSubject() : null;
    }

    /**
     * Obtiene el payload o la parte que contienen la información del token
     * @param token String
     * @return String
     */
    public String getPayload(final String token) {
        DecodedJWT decoded = getDecodedJWT(token);
        return !Objects.isNull(decoded) ? decoded.getPayload() : null;
    }

    /**
     * Método que genera el algoritmo de encriptado con su llave secreta
     *
     * @return Algorithm
     */
    private Algorithm getAlgorithmHS() {
        if (algorithmHS == null) {
            algorithmHS = Algorithm.HMAC512(getSecretKey());
        }
        return algorithmHS;
    }

    /**
     * Método que decodifica el token
     *
     * @param token String
     * @return DecodedJWT
     */
    private DecodedJWT getDecodedJWT(final String token) {
        return getVerifier().verify(token);
    }

    /**
     * Método que verifica el token
     *
     * @return JWTVerifier
     */
    private JWTVerifier getVerifier() {
        if (verifier == null) {
            verifier = JWT.require(getAlgorithmHS()).withIssuer(getLoginApiIssuer()).build();
        }
        return verifier;
    }
}
