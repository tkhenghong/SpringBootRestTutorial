package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// Configuration file
@Configuration
// Enable Swagger
@EnableSwagger2
public class SwaggerConfig {

	// This will expose a few new APIs for this SPRING
	// It will tell the devs about:
	// API documentation of this server
	// Configuration/UI
	// Swagger/resources
	// Go to your browser and type
	// http://localhost:8080/v2/api-docs (To view the whole APIs in JSON format)
	// http://localhost:8080/swagger-ui.html (To view the whole APIs in more
	// graphical view)
	@Bean
	// Bean - Docket
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2);
	}
	// Swagger 2
	// All the paths
	// All the APIs
}
