package com.shop.myshop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //Here we returned the Docket for API documentation information
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo()).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    //App Information
    private ApiInfo getInfo() {
        String tittle="Application : MyShop";
        String description="This project Developed by Ajay Kate";
        String version="1.0";
        String termOfServ="Terms Of Service";
        Contact contact=new Contact("Ajay Kate","https://myshop.com","ajaykate@gmail.com");

        return new ApiInfo(tittle,description,version,termOfServ, contact,"License","License Url", Collections.emptyList());

    }
}
