package com.example.datauiapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"controller","services"})
@EnableJpaRepositories("repository")
@EntityScan( basePackages = {"model"} )


//@ComponentScan(basePackageClasses = NationalityController.class)
public class DataUiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataUiAppApplication.class, args);
	}

}
