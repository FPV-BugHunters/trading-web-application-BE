package com.umb.trading_software.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {
	

	
    @Value("${APPLICATION_URL}")
    private String applicationUrl;
	

	@Bean
	public OpenAPI tradingDatabaseOpenAPI() {
		return new OpenAPI().info(new Info().title("Trading Application").description("Description").version("1.0")).servers(Arrays.asList(new Server().url(applicationUrl).description("Generated server url")));
	}
}