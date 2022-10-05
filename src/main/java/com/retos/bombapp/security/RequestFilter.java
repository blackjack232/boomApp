package com.retos.bombapp.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.retos.bombapp.constants.Constantes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Filtro que recibe todas las peticiones a los servicios
 */
@Service
@Slf4j
public class RequestFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * Filtro interno que valida si está o no autenticado el usuario
     *
     * @param request     HttpServletRequest
     * @param response    HttpServletResponse
     * @param filterChain FilterChain
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        request.setCharacterEncoding(Constantes.CHARSET_UTF_8);
        response.setCharacterEncoding(Constantes.CHARSET_UTF_8);

        final String authorizationHeader = request.getHeader(Constantes.AUTHORIZATION);
        String username = null;
        String jwt = null;
        String error = Constantes.TOKEN_INVALID;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtTokenUtil.getSubject(jwt);
            } catch (TokenExpiredException e) {
                error = Constantes.TOKEN_EXPIRED;
                log.error("{} A las {}", Constantes.TOKEN_EXPIRED, e.getExpiredOn());
            } catch (JWTDecodeException e) {
                log.error("{} => {}", Constantes.TOKEN_INVALID, e.getMessage());
            } catch (Exception e) {
                log.error("Error en verificación de token => " + e.getMessage());
            }
        }

        if (Objects.isNull(authorizationHeader)) {
            error = Constantes.NO_TOKEN_PROVIDE;
        }

        request.setAttribute(Constantes.TOKEN_ATTRIBUTE, error);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(jwt)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}