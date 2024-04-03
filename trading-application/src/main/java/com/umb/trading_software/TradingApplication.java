package com.umb.trading_software;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;




@SpringBootApplication
public class TradingApplication implements CommandLineRunner {

	
	public TradingApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(TradingApplication.class, args);
	}

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World");

	}
}
