package com.vbsoft.Configs.App;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration("AppConfig")
@EnableWebMvc
public class ContentConfig  implements WebMvcConfigurer {

    /**
     * Configure content negotiation options.
     *
     * @param configurer context
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.TEXT_XML).
        mediaType("text/xml", MediaType.TEXT_XML).
        mediaType("application/xml", MediaType.APPLICATION_XML);
    }
}
