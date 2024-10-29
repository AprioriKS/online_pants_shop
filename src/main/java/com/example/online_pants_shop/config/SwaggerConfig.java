package com.example.online_pants_shop.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("Online Pants Shop API")
            .version("1.0")
            .description("API для управління онлайн-магазином"))
        .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
        .components(new io.swagger.v3.oas.models.Components()
            .addSecuritySchemes("basicAuth", new SecurityScheme()
                .type(Type.HTTP)
                .scheme("basic")
                .in(In.HEADER)
                .name("Authorization")));
  }
}
