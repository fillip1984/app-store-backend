package org.home.knowledge.appstore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Rest configuration
 * <p>
 * Swagger/OpenAPI config - sample:
 * https://github.com/hmcts/spring-boot-template/blob/master/src/main/java/uk/gov/hmcts/reform/demo/config/OpenAPIConfiguration.java
 */
@Configuration
public class RestConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    OpenAPI openAPI() {
        // @formatter:off
        return new OpenAPI()
            .info(
                new Info().title(applicationName + " API")
                          .description(applicationName + " API")
                          .version("v0.1");
                        //   .license(new License().name("MIT").url("https://opensource.org/licenses/MIT"))
            );
        // @formatter:on
    }
}