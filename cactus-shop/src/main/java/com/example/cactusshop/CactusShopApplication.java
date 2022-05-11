package com.example.cactusshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CactusShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CactusShopApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public MessageSource messageSource() {
		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

		messageSource.setBasename("classpath:errors");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
}
