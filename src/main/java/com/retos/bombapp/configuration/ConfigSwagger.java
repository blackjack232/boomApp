package com.retos.bombapp.configuration;

import com.retos.bombapp.constants.Constantes;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración Swagger
 */
@Configuration
@SecurityScheme(
        name = Constantes.BEARER_AUTHENTICATION,
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class ConfigSwagger {
    /**
     * Bean para la configuración del swagger UI
     *
     * @return OpenApi
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Retos 4.0 - BombApp")
                        .description("Documentación de los servicios REST")
                        .version("v0.0.1")
                        .license(new License().name("OpenAPI 3").url("http://springdoc.org"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Aquí puede ir documentación EXTERNA")
                        .url("https://google.com"));
    }
}
