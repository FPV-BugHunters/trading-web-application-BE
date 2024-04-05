package com.umb.trading_software.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	@Bean
	public OpenAPI DatabaseOpenAPI() {
		return new OpenAPI().info(new Info().title("Trading application").description("").version("1.0"));
	}
}