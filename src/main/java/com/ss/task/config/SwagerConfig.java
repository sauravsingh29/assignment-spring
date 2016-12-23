package com.ss.task.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwagerConfig {
	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("testswager").apiInfo(apiInfo()).select()
				.paths(regex("/shopdetails.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Spring Assignmenti")
				.description(
						"Save and fetch Retail manager shop information, which internally consumer google map services api")
				.termsOfServiceUrl("https://www.dummy.com/terms-of-use")
				.contact(new Contact("Saurav Singh", "www.ss.com", "singh.saurav001@gmail.com"))
				.title("Demo Shop location").version("1.0").build();
	}
}
