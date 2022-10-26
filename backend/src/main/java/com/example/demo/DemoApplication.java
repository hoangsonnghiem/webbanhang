package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    public static class CustomPasswordEncoder implements PasswordEncoder {
        @Override
        public String encode(CharSequence plainTextPassword) {
            return BCrypt.hashpw(plainTextPassword.toString(),BCrypt.gensalt(16));
        }

        @Override
        public boolean matches(CharSequence plainTextPassword, String passwordInDatabase) {
            return BCrypt.checkpw(plainTextPassword.toString(),passwordInDatabase);
        }
    }
    
    private final class WebMvcConfigurerAdapterExtension extends WebMvcConfigurerAdapter
    {
        @Override
        public void addCorsMappings(CorsRegistry corsRegistry)
        {
            corsRegistry.addMapping("/**").allowedOrigins("http://localhost:8080");
        }
    }

    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurerAdapterExtension();
    }
}
