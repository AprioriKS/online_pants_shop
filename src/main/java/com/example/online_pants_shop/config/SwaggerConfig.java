package com.example.online_pants_shop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Online Store API", version = "1.0", description = "API для интернет-магазина"))
public class SwaggerConfig {
}
