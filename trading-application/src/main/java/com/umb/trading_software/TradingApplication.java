package com.umb.trading_software;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TradingApplication implements CommandLineRunner {

	
	public TradingApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(TradingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World");

	}
}
