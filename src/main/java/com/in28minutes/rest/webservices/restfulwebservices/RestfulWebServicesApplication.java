package com.in28minutes.rest.webservices.restfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

	// Internationalization
	// Step 1: Setup locale, to determine which language as default
	@Bean
	public LocaleResolver localeResolver() {
		// SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		// Replace above to below
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	// Step 3: After create language resource files, create a method to read those
	// files

	// Note that the tutorial overrides bundleMessageSource() method, but here it's
	// not working
	// Overrides messageSource() method solves the problem.
	// Commented: Because you can do it in application.properties*
//	@Bean
//	public ResourceBundleMessageSource messageSource() {
//		ResourceBundleMessageSource messsageSource = new ResourceBundleMessageSource();
//		// NOTE: You need to set a common shared name so you can tell SPRING all those
//		// files with that name is the translation files
//		messsageSource.setBasename("messages");
//		return messsageSource;
//	}

}
