package com.locationsapi.interfaces.adapter.documentation;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
class SwaggerConfig {

  @Bean
  Docket memberGetMemberApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.ant("/api/v1/**"))
        .build()
        .pathMapping("/")
        .directModelSubstitute(LocalDateDeserializer.class, String.class)
        .genericModelSubstitutes(ResponseEntity.class)
        .useDefaultResponseMessages(false)
        .apiInfo(getApiInfo())
        .enableUrlTemplating(false);
  }

  private ApiInfo getApiInfo() {
    return new ApiInfo(
        "Location Service",
        "Responsible for locations data structure.",
        "1.0.0",
        null,
        null,
        null,
        null,
        Collections.emptyList()
    );
  }
}
