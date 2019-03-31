package com.fatec.tcc;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.fatec.tcc" })
public class GuiaComercialWsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GuiaComercialWsApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}

}
