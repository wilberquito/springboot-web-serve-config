package com.example.demo;

import com.example.demo.configuration.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class StaticApplication {

	public static void main(String[] args) {
		SpringApplication.run(StaticApplication.class, args);
	}
}
