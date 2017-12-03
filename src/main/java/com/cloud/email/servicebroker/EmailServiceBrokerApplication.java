package com.cloud.email.servicebroker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class EmailServiceBrokerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceBrokerApplication.class, args);
	}
}
