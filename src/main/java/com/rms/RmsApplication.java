package com.rms;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RmsApplication.class, args);
		System.out.println("Server Is runing");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
