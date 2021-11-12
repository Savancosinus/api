package com.hardisgroup.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Fichier de configuration pour Swagger.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final String TAG_FICHIER_CONTROLLER = "FichierController";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // Exclusion des exceptions Spring
                .apis(RequestHandlerSelectors.basePackage("com.hardisgroup.api"))
                // Exclusion des méthodes de tests
                .paths(PathSelectors.regex("/api.*"))
                // Tags (controller)
                .build().tags(new Tag(TAG_FICHIER_CONTROLLER, "Controller de fichier pour Hardis Group"));
    }
}
