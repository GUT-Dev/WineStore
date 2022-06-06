package com.winestore.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(
                new Info()
                    .title("WineStore")
                    .version("0.0.1-SNAPSHOT")
                    .contact(
                        new Contact()
                            .email("email")
                            .name("name")
                    )
            );
    }
}
