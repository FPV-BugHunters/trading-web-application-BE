package com.umb.trading_software;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;


@SpringBootApplication
@EnableMethodSecurity
public class TradingApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(Demo3Application.class);

	public TradingApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(TradingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Hello Spring Boot");
	}
}
