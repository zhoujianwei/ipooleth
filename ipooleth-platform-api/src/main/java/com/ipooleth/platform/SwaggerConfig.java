package com.ipooleth.platform;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * Created by abc123 on 16/6/24.
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket newsApi() {

        //http://localhost:8080/swagger-ui.html#/
        return new Docket(DocumentationType.SWAGGER_2)
                //.groupName("greetings")
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, new ArrayList())
                .genericModelSubstitutes(DeferredResult.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .tags(new Tag("tag name ","tag desc"))
                .apiInfo(apiInfo()).enable(true).enableUrlTemplating(false)
                .forCodeGeneration(false)
                .enableUrlTemplating(false)
                .select()
                .paths(new Predicate<String>() {
                    @Override
                    public boolean apply(String s) {
                        if (null != s && s.equals("/error"))
                            return false;
                        return true;
                    }
                })
                .build();
    }




    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("IPOOLETH INTERFACE SERVICE")
                .description("")
                .termsOfServiceUrl("")
                .contact("ibichi.com")
                .license("")
                .licenseUrl("")
                .version("1.0")
                .build();
    }


}