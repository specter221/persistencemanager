package com.distributedpipeline.persistence.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@EnableSwagger2
public class Swagger {
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.distributedpipeline.persistence"))
                .paths(PathSelectors.regex("/workflow.*"))
                .build();
             
    }
	
	private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot REST API",
                "Persistence Manager Microservice",
                "2.0",
                "Terms of service",
                new Contact("Akshay Vaibhav", "https://github.com/specter221", ""),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}



    

