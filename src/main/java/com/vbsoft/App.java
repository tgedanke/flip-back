package com.vbsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

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
        SpringApplication.run(App.class, args);
    }

}
