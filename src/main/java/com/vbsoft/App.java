package com.vbsoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

/**
 * Root application class.
 */
@SpringBootApplication
public class App
{
    /**
     * Main method.
     * @param args Application arguments
     */
    public static void main( String[] args )
    {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        System.out.println(Arrays.toString(context.getEnvironment().getActiveProfiles()));
        System.out.println(context.getEnvironment().getProperty("spring.datasource.url"));
    }

}
