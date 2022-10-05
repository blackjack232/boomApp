package com.retos.bombapp.security;

import com.retos.bombapp.constants.CodigoRespuestaApi;
import com.retos.bombapp.constants.Constantes;
import com.retos.bombapp.models.RespuestaDTO;
import com.retos.bombapp.services.UtilService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

/**
 * Configuraciones para la seguridad de las rutas de los servicios
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    /**
     * Bean para configuración de rutas autenticadas
     *
     * @param http          HttpSecurity
     * @param requestFilter RequestFilter
     * @param utilService   UtilService
     * @return SecurityFilterChain
     * @throws Exception Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, RequestFilter requestFilter, UtilService utilService) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
                    RespuestaDTO respuestaDTO = RespuestaDTO
                            .builder()
                            .code(CodigoRespuestaApi.NO_AUTHORIZED.getCode())
                            .message((String) request.getAttribute(Constantes.TOKEN_ATTRIBUTE))
                            .status(CodigoRespuestaApi.NO_AUTHORIZED.getHttpStatus().value())
                            .build();
                    response.setStatus(401);
                    response.setHeader(Constantes.CONTENT_TYPE, Constantes.APPLICATION_JSON);
                    response.getWriter().write(utilService.writeToString(respuestaDTO));
                })
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/acceso/**", "/swagger-ui/**", "/swagger-ui.html", "/v3/**",
                        "/usuarios/recuperar-contrasena", "/configuracion-inicial/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * Configuración de los CORS para el tipo de solicitudes
     * Lo utiliza spring security si los CORS están habilitados
     *
     * @return CorsFilter
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> initCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList(
                HttpHeaders.ORIGIN,
                HttpHeaders.REFERER,
                HttpHeaders.USER_AGENT,
                HttpHeaders.CACHE_CONTROL,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT,
                HttpHeaders.AUTHORIZATION
        ));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedOriginPatterns(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
