package com.shopstyle.msbffshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	 public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	            .useDefaultResponseMessages(false)
	            .select()
	            .apis(RequestHandlerSelectors.basePackage("com.shopstyle.msbffshop"))
	            .paths(PathSelectors.ant("/**"))
	            .build()
	            .apiInfo(apiInfo());
	 }
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Shop Style - MS BFF-Shop Microservice")
				.description("MS-BFFShop")
				.version("1.0")
				.build();
	}
}
