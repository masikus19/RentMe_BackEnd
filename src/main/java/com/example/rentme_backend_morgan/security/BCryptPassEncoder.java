package com.example.rentme_backend_morgan.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BCryptPassEncoder {
	
	@Bean
	public PasswordEncoder bCryptEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
