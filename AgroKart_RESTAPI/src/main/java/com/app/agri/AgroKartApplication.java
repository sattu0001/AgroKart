package com.app.agri;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EntityScan("com.app.agri.entity")
@EnableJpaRepositories("com.app.agri.repository")//to create repository
public class AgroKartApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgroKartApplication.class, args);
	}
	
	//creating bean of Model mapper  applied on method and returns object of Modelmapper

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
