package com.ksu.grad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class HumanResourceManagementApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(HumanResourceManagementApplication.class, args);
	}
	
	
	 @Bean
	 public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	 }
	 
	 @Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(HumanResourceManagementApplication.class);
		 
	 }
}
