package com.ep.backend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	// to use model mapper we have to create its object and make the class as bean
//	 now we can autowire the object of mapper anywhere
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

}
