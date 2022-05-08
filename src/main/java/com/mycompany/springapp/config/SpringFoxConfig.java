package com.mycompany.springapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2//EnableSwagger2 means all the controller classes will be scanned and all the documentation will be generated
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.mycompany.springapp"))
                .paths(PathSelectors.any())
                .build();
    }
}
