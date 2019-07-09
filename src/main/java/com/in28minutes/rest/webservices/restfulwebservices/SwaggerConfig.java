package com.in28minutes.rest.webservices.restfulwebservices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// Configuration file
@Configuration
// Enable Swagger
@EnableSwagger2
public class SwaggerConfig {

	// Copied from ApiInfo.class
	public static final Contact DEFAULT_CONTACT = new Contact("Ranga Karanam", "http://www.in28minutes.com",
			"in28minutes@gmail.com");
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Awesome API Title", "Awesome Api Documentation", "1.0",
			"urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
			new ArrayList<VendorExtension>());
	// Create a string that tells the devs that this APIs are accepting and
	// producing JSON and XML
	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(
			Arrays.asList("application/json", "application/xml"));

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
		// return new Docket(DocumentationType.SWAGGER_2);
		// To give more info abou the API, add apiInfo() method:
		// produces() to tell the devs that this APIs are accepting and producing JSON
		// and XML
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES);
	}
	// Swagger 2
	// All the paths
	// All the APIs
}
