package com.example.userspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class UserSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserSpringbootApplication.class, args);
	}

}
