package com.example.sprint3.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            // Configuración del objeto OpenAPI (similar al Docket)
            .info(getApiInfo()); 
            // NOTA: SpringDoc infiere automáticamente los paquetes y paths del proyecto.
            // No se necesita un método .select() explícito como en Springfox.
    }

    private Info getApiInfo() {
        return new Info()
            .title("Order Service API (Digital NAO)") // Título del API
            .description("CRUD de órdenes para el equipo Digital NAO.") // Descripción
            .version("1.0") // Versión
            .termsOfService("http://digitalnao.com/terms") // URL de Términos
            .contact(new Contact() // Contacto (similar al objeto Contact de Springfox)
                .name("Digital NAO Team")
                .url("https://digitalnao.com")
                .email("apis@digitalnao.com"))
            .license(new License() // Licencia (similar al objeto License de Springfox)
                .name("Apache 2.0")
                .url("http://www.apache.org/licenses/LICENSE-2.0.html"));
    }
}