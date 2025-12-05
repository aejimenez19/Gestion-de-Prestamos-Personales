package com.aejimenezdev.gestionDePrestamosPersonales.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestión de Préstamos Personales API")
                        .description("API para gestionar préstamos, pagos y clientes, siguiendo Clean Architecture.")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Álvaro Jiménez")
                                .email("aejjimenez19@gmail.com")
                                .url("https://aejimenez.online"))
                        .license(new License().name("MIT License").url("https://opensource.org/licenses/MIT"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Documentación completa del proyecto en GitHub")
                        .url("https://github.com/aejimenez19/Gestion-de-Prestamos-Personales")
                );
    }
}
