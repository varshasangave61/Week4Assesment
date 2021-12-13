package com.example.CustomerProducer;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("package_for_repository")
@SpringBootApplication
@ComponentScan("com.example.Repository.CustomerRepository")
public class CustomerProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerProducerApplication.class, args);
	}

}
