package com.vbsoft.Config;

import com.google.common.base.Predicates;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import javax.annotation.Resource;
import java.util.function.Predicate;

@Configuration
@ConditionalOnProperty(prefix = "swagger", havingValue = "true", name = "enabled")
@EnableConfigurationProperties(SwaggerProperties.class)
@EnableSwagger2
@RequiredArgsConstructor
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Resource
    private SwaggerProperties properties;

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(properties.getHost())
                .tags(new Tag("Samsung API", "1.0"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.vbsoft.Controller"))
                .paths(PathSelectors.regex("/*.*"))
                .build()
                .apiInfo(new ApiInfoBuilder().title("Samsung API").version(properties.getProjectVersion()).build());
    }


}
