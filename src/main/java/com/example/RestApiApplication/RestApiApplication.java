package com.example.RestApiApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
	"com.example.RestApiApplication.Repositories",
	"com.example.RestApiApplication.Services"
})
//@EnableAutoConfiguration
public class RestApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}
}
