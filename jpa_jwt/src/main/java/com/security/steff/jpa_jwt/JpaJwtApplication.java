package com.security.steff.jpa_jwt;

import com.security.steff.jpa_jwt.repositories.UserRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class JpaJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaJwtApplication.class, args);
	}

}
